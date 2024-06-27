package com.icezhg.sunflower.controller.system;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.domain.AvatarPicture;
import com.icezhg.sunflower.domain.Picture;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.Profile;
import com.icezhg.sunflower.pojo.ProfilePasswd;
import com.icezhg.sunflower.security.UserInfo;
import com.icezhg.sunflower.service.PictureService;
import com.icezhg.sunflower.service.ProfileService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhongjibing on 2022/09/14.
 */
@RestController
@RequestMapping("/user/profile")
public class ProfileController {

    private final ProfileService profileService;

    private PictureService pictureService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Autowired
    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping
    public Profile profile() {
        return profileService.buildProfile();
    }

    @PutMapping
    @Operation(title = "profile modification", type = OperationType.UPDATE, saveResult = false)
    public Profile updateProfile(@Validated @RequestBody Profile profile) {
        return profileService.updateProfile(profile);
    }


    @PostMapping("/updatePasswd")
    @Operation(title = "change password", type = OperationType.UPDATE, saveParameter = false)
    public void updatePasswd(@RequestBody ProfilePasswd profilePasswd) {
        profileService.updatePasswd(profilePasswd);
    }

    @PostMapping("/avatar")
    @Operation(title = "avatar uploading", type = OperationType.UPLOAD, saveResult = false)
    public Profile avatar(MultipartFile file) {
        UserInfo userInfo = SecurityUtil.currentUserInfo();
        Picture picture = pictureService.save(file);
        AvatarPicture avatarPicture = new AvatarPicture(userInfo.getPicture(), picture.getId());
        profileService.updateAvatar(avatarPicture);
        return profileService.buildProfile();
    }
}
