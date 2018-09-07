package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.Issue;
import ikon.ikon.Model.IssueType;
import ikon.ikon.Model.Products;

/**
 * Created by ic on 9/6/2018.
 */

public interface IssueTybeView {

    void GetIssuetybe(List<IssueType> a);
    void showErrorIssuetybe(String error);
}
