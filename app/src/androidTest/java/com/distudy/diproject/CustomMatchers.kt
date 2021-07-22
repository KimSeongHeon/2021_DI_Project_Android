package com.distudy.diproject

import android.os.IBinder
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Root
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class CustomMatchers {
    companion object {
        fun withItemCount(count: Int): BoundedMatcher<View, RecyclerView> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText("RecyclerView with item count: $count")
                }

                override fun matchesSafely(item: RecyclerView?): Boolean {
                    return item?.adapter?.itemCount == count
                }
            }
        }

        fun withTextViewContent(text: String): BoundedMatcher<View, RecyclerView> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText("RecyclerView with item count: $text")
                }

                override fun matchesSafely(item: RecyclerView?): Boolean {
                    val itemView = item?.findViewHolderForAdapterPosition(0)?.itemView
                    return itemView?.findViewById<TextView>(R.id.user_name)?.text == text
                }
            }
        }

    }
}

/**
 * 분석 필요. 배껴
 */
open class ToastMatcher(private val message: String) : TypeSafeMatcher<Root?>() {
    override fun matchesSafely(root: Root?): Boolean {
        root ?: return false
        val type: Int = root.windowLayoutParams.get().type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowToken: IBinder = root.decorView.windowToken
            val appToken: IBinder = root.decorView.applicationWindowToken
            if (windowToken === appToken) {
                // means this window isn't contained by any other windows.
                return true
            }
        }
        return false
    }

    override fun describeTo(description: org.hamcrest.Description?) {
        description?.appendText(message)
    }

}