/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.utils;

/**
 *
 * @author User
 */
public class MyApplicationConstant {

    public class DispatchFeatures {

        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginController";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastNameController";
        public static final String DELETE_ACCOUNT_CONTROLLER = "deleteController";
        public static final String UPDATE_ACCOUNT_CONTROLLER = "updateController";
        public static final String STARTUP_SERVLET = "startUpController";
        public static final String ADD_PRODUCT_TO_CART_SERVLET = "addProductToCartController";
        public static final String LOGOUT_CONTROLLER = "logOutController";
        public static final String VIEW_CART_PAGE = "viewCartPage";
        public static final String REMOVE_PRODUCT_FROM_CART_SERVLET = "removeProductFromCartController";
        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccountController";
        public static final String CHECK_OUT_CONTROLLER = "checkOutController";
    }

    public class LoginFeatures {

        public static final String INVALID_PAGE = "invalidPage";
        public static final String SEARCH_PAGE = "searchPage";
    }

    public class SearchFeatures {

        public static final String SEARCH_RESULT_PAGE = "searchPage";
    }

    public class DeleteFeatures {

        public static final String ERROR_PAGE = "errorsPage";
    }

    public class UpdateFeatures {

        public static final String ERROR_PAGE = "errorsPage";
    }

    public class AddProductFeatures {

        public static final String SHOW_PRODUCT = "showProductsController";
        public static final String LOGIN_PAGE = "";
    }

    public class CheckOutFeatures {

        public static final String LOGIN_PAGE = "";
        public static final String CHECKOUT_PAGE = "checkOut";
        public static final String SHOW_PRODUCT = "showProductsController";
    }

    public class CreateAccountFeatures {

        public static final String ERROR_PAGE = "createAccountPage";
        public final static String LOGIN_PAGE = "";
    }

    public class LogoutFeatures {

        public static final String LOGIN_PAGE = "";
    }

    public class ShowProductFeatures {

        public static final String SHOPPING_PAGE = "shoppingPage";
    }

    public class StartUpFeatures {

        public static final String LOGIN_PAGE = "";
        public static final String SEARCH_PAGE = "searchPage";

    }
    
    public class ViewCartFeatures {

        public static final String SHOPPING_PAGE = "shoppingPage";
        public static final String VIEW_CART_PAGE = "viewCartPage";

    }
}
