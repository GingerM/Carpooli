package ma.carpooli.carpooli;

import ma.carpooli.carpooli.utils.ImageUploadInfo;

/**
 * Created by Cassandra on 1/2/2018.
 */

public class UserData {
    String verificationCode;
    String authUId;
    ImageUploadInfo imageUploadInfo;

    public ImageUploadInfo getImageUploadInfo() {
        return imageUploadInfo;
    }

    public void setImageUploadInfo(ImageUploadInfo imageUploadInfo) {
        this.imageUploadInfo = imageUploadInfo;
    }

    public String getAuthUId() {
        return authUId;
    }

    public void setAuthUId(String authUId) {
        this.authUId = authUId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
