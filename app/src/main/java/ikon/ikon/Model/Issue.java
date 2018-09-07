package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 9/6/2018.
 */

public class Issue {


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("issueType")
    @Expose
    private List<IssueType> issueType = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<IssueType> getIssueType() {
        return issueType;
    }

    public void setIssueType(List<IssueType> issueType) {
        this.issueType = issueType;
    }
}
