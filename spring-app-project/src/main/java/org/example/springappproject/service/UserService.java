package org.example.springappproject.service;

import org.example.springappproject.dto.request.UserUpdateRequestDto;
import org.example.springappproject.dto.response.user.get.UserInfoDto;
import org.example.springappproject.dto.response.user.get.UserInfoNotCommentDto;
import org.example.springappproject.dto.response.user.get.UserResponseDto;
import org.example.springappproject.dto.response.user.get.UserResponseNotCommentDto;
import org.example.springappproject.dto.response.user.update.UserUpdateInfoDto;
import org.example.springappproject.dto.response.user.update.UserUpdateResponseSuccessDto;
import org.example.springappproject.entity.User;
import org.example.springappproject.exception.*;
import org.example.springappproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ユーザ情報を取得する
    public User getUserByUserId(String userId){
        return userRepository.selectUserByUserId(userId);
    }

    // ユーザ情報を登録する
    public void addUser(String userId, String password){
        // 既に同じユーザが存在するか
        if(userRepository.selectUserByUserId(userId) != null){
            throw new UserAlreadyExistsException();
        }

        // フィールド情報のバリデーションチェック
        // 存在するか
        if(userId == null || password == null){
            throw new SignupValidationException("Required user_id and password");
        }

        // 値の長さを確認
        if(!(userId.length() >= 6 && userId.length() <= 20 && password.length() >= 8 && password.length() <= 20)){
            throw new SignupValidationException("Input length is incorrect");
        }

        // 文字の種類の判定
        if(!userId.matches("^[A-Za-z0-9]+$") || !password.matches("^[\\x21-\\x7E]+$")){
            throw new SignupValidationException("Incorrect character pattern");
        }



        String encodedPassword = passwordEncoder.encode(password);
        userRepository.insertUser(userId, encodedPassword);
    }

    // ユーザ情報を更新する
    public void updateUser(String userId, UserUpdateRequestDto userUpdateRequestDto, String base64UserId){

        // パスパラメータのUserIdとトークンのUserIdか異なる場合
        if(!userId.equals(base64UserId)){
            throw new AccessDeniedException();
        }

        // UserIdやパスワードを更新しようとしている
        if(userUpdateRequestDto.getUserId() != null || userUpdateRequestDto.getPassword() != null){
            throw new UserUpdateErrorException("Not updatable user_id and password");
        }


        // 現在のユーザ情報を取得
        User user = userRepository.selectUserByUserId(userId);

        // ユーザが存在しない
        if(user == null){
            throw new UserNotFoundException();
        }

        // nicknameとcommentが両方存在しない場合
        if(userUpdateRequestDto.getNickname() == null && userUpdateRequestDto.getComment() == null){
            throw new UserUpdateErrorException("Required nickname or comment");
        }

        // バリデーションチェック
        if(!(userUpdateRequestDto.getNickname().length() <= 30 && userUpdateRequestDto.getNickname().matches("^[^\\p{Cntrl}]*$"))){
            throw new UserUpdateErrorException("String length limit exceeded or containing invalid characters");
        }
        if(!(userUpdateRequestDto.getComment().length() <= 100 && userUpdateRequestDto.getComment().matches("^[^\\p{Cntrl}]*$"))){
            throw new UserUpdateErrorException("String length limit exceeded or containing invalid characters");
        }

        // nicknameをUseridに戻す又はcommentを初期化する処理
        if(userUpdateRequestDto.getNickname() == null){
            // 現在のニックネームを格納
            userUpdateRequestDto.setNickname(user.getNickname());
        }else if(userUpdateRequestDto.getNickname().isEmpty()){
            // userIdに初期化
            userUpdateRequestDto.setNickname(user.getUserId());
        }

        if(userUpdateRequestDto.getComment() == null){
            // 現在のコメントを格納
            userUpdateRequestDto.setComment(user.getComment());
        }else if(userUpdateRequestDto.getComment().isEmpty()){
            userUpdateRequestDto.setComment("");
        }

        // ユーザ情報を更新する
        User updateUser = new User(userId,"",userUpdateRequestDto.getNickname(),userUpdateRequestDto.getComment(), LocalDateTime.now());
        userRepository.updateUser(updateUser);
    }

    // User情報Update用のレスポンス情報を返す
    public UserUpdateResponseSuccessDto getUserUpdateResponseSuccessDto(String userId){
        // ユーザ情報取得
        User user = userRepository.selectUserByUserId(userId);
        UserUpdateResponseSuccessDto userUpdateResponseSuccessDto = new UserUpdateResponseSuccessDto();

        userUpdateResponseSuccessDto.setMessage("User successfully updated");
        UserUpdateInfoDto userUpdateInfoDto = new UserUpdateInfoDto(user.getUserId(),user.getNickname(),user.getComment());
        userUpdateResponseSuccessDto.setUser(userUpdateInfoDto);

        return userUpdateResponseSuccessDto;
    }



        // ユーザ情報を適切なResponseDtoに詰め替える
    public Object ChengeResponseDto(User user){
        String userId = user.getUserId();
        String nickname = user.getNickname();
        String comment = user.getComment();

        if(nickname.isEmpty()){
            nickname = userId;
        }

        if(comment.isEmpty()){
            // コメントが設定されていない
            UserResponseNotCommentDto userResponseNotCommentDto = new UserResponseNotCommentDto();
            userResponseNotCommentDto.setMessage("User details by user_id");
            UserInfoNotCommentDto userInfoNotCommentDto = new UserInfoNotCommentDto(userId, nickname);
            userResponseNotCommentDto.setUser(userInfoNotCommentDto);
            return userResponseNotCommentDto;
        }else{
            // コメントが設定されている
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setMessage("User details by user_id");
            UserInfoDto userInfoDto = new UserInfoDto(userId, nickname,comment);
            userResponseDto.setUser(userInfoDto);
            return userResponseDto;
        }

    }

    // ユーザを削除する
    public void deleteUser(String userId){
        // 現在ユーザ情報を取得
        User user = userRepository.selectUserByUserId(userId);

        if(user == null){
            return;
        }

        userRepository.deleteUser(userId);
    }




}

