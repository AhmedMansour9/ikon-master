package ikon.ikon.PreSenter;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import ikon.ikon.Model.Count;
import ikon.ikon.Model.IssueResponse;
import ikon.ikon.Retrofit.ApiCLint;
import ikon.ikon.Retrofit.Apiinterface;
import ikon.ikon.Viewes.AccessoriesView;
import ikon.ikon.Viewes.CountView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ic on 9/12/2018.
 */

public class CounterPresenter {
    Count cont;
    public CounterPresenter(Context context, Count cont)
    {
        this.cont=cont;

    }

    public void GetCount(String connn) {

        cont.count(connn);
    }

}
