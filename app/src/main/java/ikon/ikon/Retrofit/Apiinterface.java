package ikon.ikon.Retrofit;

import java.util.Map;

import ikon.ikon.Model.AccessoriesResponse;
import ikon.ikon.Model.AccessorySubCategoryResoonse;
import ikon.ikon.Model.BannerResponse;
import ikon.ikon.Model.ColorResponse;
import ikon.ikon.Model.GetPriceResponse;
import ikon.ikon.Model.IssueResponse;
import ikon.ikon.Model.IssueTubeEnglishResponse;
import ikon.ikon.Model.ListOrderResponse;
import ikon.ikon.Model.ListOrderShoppingResponse;
import ikon.ikon.Model.MyorderShopingResponse;
import ikon.ikon.Model.OrderResponse;
import ikon.ikon.Model.PeoductResponse;
import ikon.ikon.Model.ShowProductsResponse;
import ikon.ikon.Model.SpartsResponse;
import ikon.ikon.Model.UserLoginResponse;
import ikon.ikon.Model.UserRegisterResponse;
import ikon.ikon.Model.phonesResponse;
import ikon.ikon.Model.RegisterFaceResponse;
import ikon.ikon.PreSenter.ColorPresenter;
import ikon.ikon.PreSenter.OrderShoppinPresenter;
import ikon.ikon.PreSenter.ShowOrdersByid_Presenter;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by HP on 04/09/2018.
 */

public interface Apiinterface {


    @POST("signupMobile")
    Call<UserRegisterResponse> register(@QueryMap Map<String,String> queryMab);

    @POST("login")
    Call<UserLoginResponse> Login(@QueryMap Map<String,String> queryMab);

    @POST("signupMobileFacebook")
    Call<RegisterFaceResponse> RegisterFace_Book(@QueryMap Map<String,String> queryMab);

    @POST("signupMobileGoogle")
    Call<UserLoginResponse> RegisterGoogle(@QueryMap Map<String,String> queryMab);

    @POST("sparePartsList")
    Call<PeoductResponse> GetProducts(@QueryMap Map<String,String> queryMab);

    @POST("issueTypeList")
    Call<IssueResponse> GetIssueTybe(@QueryMap Map<String,String> queryMab);

    @POST("issueTypeList")
    Call<IssueTubeEnglishResponse> GetIssueEnglish(@QueryMap Map<String,String> queryMab);

    @POST("issuePrice")
    Call<GetPriceResponse> GetPrice(@QueryMap Map<String,String> queryMab);

    @POST("showProductShop")
    Call<phonesResponse> GetPHones(@QueryMap Map<String,String> queryMab);

    @POST("getAccessoriesCategories")
    Call<AccessoriesResponse> GetAccessories(@QueryMap Map<String,String> queryMab);

    @POST("showAccessoriesShopByCatID")
    Call<AccessorySubCategoryResoonse> GetSubCategories(@QueryMap Map<String,String> queryMab);


    @POST("showSparePartsShop")
    Call<SpartsResponse> GetSparts(@QueryMap Map<String,String> queryMab);

    @POST("maintenanceOrder")
    Call<OrderResponse> Showorder(@QueryMap Map<String,String> queryMab);

    @POST("shopOrder")
    Call<OrderShoppinPresenter> Ordershop(@QueryMap Map<String,String> queryMab);

    @POST("colorList")
    Call<ColorResponse> GetColors(@QueryMap Map<String,String> queryMab);

    @POST("mobileBanner")
    Call<BannerResponse> GetBanner(@QueryMap Map<String,String> queryMab);

    @POST("listMaintenanceOrder")
    Call<ListOrderResponse> GetListOrder(@QueryMap Map<String,String> queryMab);
    @POST("listShopOrder")
    Call<ListOrderShoppingResponse> GetListOrderShopping(@QueryMap Map<String,String> queryMab);

    @POST("productByID")
    Call<ShowProductsResponse> GetListOrderShoppingById(@QueryMap Map<String,String> queryMab);

    @POST("orderShopCustomer")
    Call<MyorderShopingResponse> GetListMyOrderShoping(@QueryMap Map<String,String> queryMab);


}

