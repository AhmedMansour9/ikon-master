package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 9/8/2018.
 */

public class IssueTybeen {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("issueType")
    @Expose
    private List<IssueTybeEnglish> issueType = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<IssueTybeEnglish> getIssueType() {
        return issueType;
    }

    public void setIssueType(List<IssueTybeEnglish> issueType) {
        this.issueType = issueType;
    }
}
