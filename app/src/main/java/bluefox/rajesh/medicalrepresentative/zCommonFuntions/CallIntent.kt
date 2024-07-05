package bluefox.rajesh.medicalrepresentative.zCommonFuntions

import android.app.Activity
import android.content.Context
import android.content.Intent
import bluefox.rajesh.medicalrepresentative.homeModule.HomeActivity
import bluefox.rajesh.medicalrepresentative.homeModule.ProfileActivity
import bluefox.rajesh.medicalrepresentative.loginModule.LoginActivity
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.AddItemActivity
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.CustomerDetailsActivity
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.NewOrderActivity
import bluefox.rajesh.medicalrepresentative.salesModule.outstanding.OutStandingActivity
import bluefox.rajesh.medicalrepresentative.salesModule.ordersList.OrdersListActivity

object CallIntent {
    fun goToHomeActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun goToProfileActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, ProfileActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun goToLoginActivityandKillMe(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun goToNewOrderActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, NewOrderActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun goToOrdersListActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, OrdersListActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }


    fun goToCustomerDetailsActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, CustomerDetailsActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun goToAddItemActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, AddItemActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun goToOutStandingActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, OutStandingActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

}
