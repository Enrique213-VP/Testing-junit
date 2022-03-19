package google.android.svape.codelab_dogglers


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import google.android.svape.codelab_dogglers.BaseTest.DrawableMatcher.withDrawable
import google.android.svape.codelab_dogglers.data.DataSource
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import java.lang.IllegalStateException


open class BaseTest {

    val lastPosition = DataSource.painters.size - 1


    /*
     * Miramos el contenido de una carta
     *
     * @param name
     * @param years el string que se muestra en pantalla
     * @param artwork el string que se muestra en pantalla
     * @param imageResource el id de la imagen
     */

    private fun hasListItemContent(
        name: String,
        years: String,
        artwork: String,
        imageResourceId: Int
    ) {
        onView(withText(name))
            .check(matches(isDisplayed()))
        onView(withText(years))
            .check(matches(isDisplayed()))
        onView(withText(artwork))
            .check(matches(isDisplayed()))
        onView(withDrawable(imageResourceId))
            .check(matches(isDisplayed()))

    }

    /*
    check first Card
     */

    fun checkFirstPosition() {
        hasListItemContent("Alexander Cabanel", "Año: 1823 - 1889",
            "Obra: Fallen angel", R.drawable.alexander_cabanel)
    }

    /*
      Matcher personalizado para encontrar drawable
     */

    object DrawableMatcher {
        /**
         * Invokes the [RecyclerViewAssertion] to check the RecyclerView has the correct count
         *
         * @param count The expected number of items in the RecyclerView adapter
         */
        fun hashItemCount(count: Int): ViewAssertion {
            return RecyclerViewAssertion(count)
        }


        fun withDrawable(@DrawableRes resourceId: Int): Matcher<View> {
            return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
                override fun describeTo(description: Description?) {
                    description!!.appendText("has drawable resource $resourceId")
                }

                override fun matchesSafely(imageView: ImageView): Boolean {
                    return isSameBitmap(imageView, imageView.drawable, resourceId)
                }
            }
        }

        private fun isSameBitmap(item: View, drawable: Drawable?, expectedResId: Int): Boolean {
            val image = item as ImageView
            if (expectedResId < 0) {
                return image.drawable == null
            }
            val expectedDrawable: Drawable? = ContextCompat.getDrawable(item.context, expectedResId)
            if (drawable == null || expectedDrawable == null) {
                return false
            }

            // In the case we are not checking a vector drawable, we can get the bitmap directly

            if (drawable is BitmapDrawable && expectedDrawable is BitmapDrawable) {
                val found = drawable.bitmap
                val expected = expectedDrawable.bitmap
                return found.sameAs(expected)
            }

            // Make tint consistent just in case they differ

            drawable.setTint(android.R.color.black)
            expectedDrawable.setTint(android.R.color.black)
            val bitmap = getBitmap(drawable)
            val expectedBitmap = getBitmap(expectedDrawable)
            return bitmap.sameAs(expectedBitmap)
        }

        /**
         * Convert vector drawable to bitmap
         * @param drawable vector drawable
         */

        private fun getBitmap(drawable: Drawable): Bitmap {
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }

        /**
         * Custom view assertion to check:
         * The RecyclerView exists
         * The RecyclerView has an adapter
         * The adapter contains the expected number of items
         *
         * @param count The expected number of adapter items
         */


        private class RecyclerViewAssertion(private val count: Int) : ViewAssertion {
            override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }
                if (view !is RecyclerView) {
                    throw IllegalStateException("La vista no esta en el RecyclerView")
                }

                if (view.adapter == null) {
                    throw IllegalStateException("El adaptador no esta asignado en el RecyclerView")
                }

                //Check item count

                ViewMatchers.assertThat(
                    "RecyclerView item count",
                    view.adapter?.itemCount,
                    CoreMatchers.equalTo(count)
                )
            }
        }
    }
}