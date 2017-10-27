package lab08.kmitl.chayanon58070021.espresso;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCase1() {

        // โดยไม่กรอก Name และ Age กดปุ่ม ADDED จะต้องเจอ Please Enter user info

        onView(withId(R.id.buttonAdded)).check(matches(isDisplayed()));

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));

        onView(withId(android.R.id.button1)).perform(click());

    }

    @Test
    public void testCase2() {

        //โดยไม่กรอก Name และ Age=20 กดปุ่ม ADDED จะต้องเจอ Please Enter user info

        onView(withId(R.id.editTextAge)).perform(replaceText("20"));

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));

        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void testCase3() {

        //ยังไม่มีการเพิ่ม UserInfo และกด GO TO LIST จะเจอ Not Found

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withId(R.id.textNotFound)).check(matches(withText("Not Found")));
    }

    @Test
    public void testCase4() {

        // โดยไม่กรอก Age และ Name=Ying กดปุ่ม ADDED จะต้องเจอ Please Enter user info

        onView(withId(R.id.editTextName)).perform(replaceText("Ying"));

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(android.R.id.message)).check(matches(withText("Please Enter user info")));

        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void testCase5() {

        // โดยกรอก Name=Ying และ Age=20 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Ying อายุ 20 เป็นตัวแรก
        // atPositionOnView = 0

        onView(withId(R.id.editTextName)).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textName))
                .check(matches(withText("Ying")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textAge))
                .check(matches(withText("20")));
    }

    @Test
    public void testCase6() {

        //โดยกรอก Name=Ladarat และ Age=20 กดปุ่ม ADDED และกด GO TO LIST
        // จะต้องเจอ Ladarat อายุ 20 ใน ListView ลำดับที่ 2

        onView(withId(R.id.editTextName)).perform(click());

        onView(withId(R.id.editTextName)).perform(replaceText("Ladarat"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textName))
                .check(matches(withText("Ladarat")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textAge))
                .check(matches(withText("20")));


    }

    @Test
    public void testCase7() {

        // โดยกรอก Name=Somkait และ Age=80 กดปุ่ม ADDED และกด GO TO LIST
        // จะต้องเจอ Somkait อายุ 80 ใน ListView ลำดับที่ 3

        onView(withId(R.id.editTextName)).perform(click());

        onView(withId(R.id.editTextName)).perform(replaceText("Somkait"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textName))
                .check(matches(withText("Somkait")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textAge))
                .check(matches(withText("80")));
    }

    @Test
    public void testCase8() {

        // โดยกรอก Name=Prayoch และ Age=60 กดปุ่ม ADDED และกด GO TO LIST
        // จะต้องเจอ Prayoch อายุ 60 ใน ListView ลำดับที่ 4

        onView(withId(R.id.editTextName)).perform(click());

        onView(withId(R.id.editTextName)).perform(replaceText("Prayoch"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textName))
                .check(matches(withText("Prayoch")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textAge))
                .check(matches(withText("60")));

    }

    @Test
    public void testCase9() {

        // โดยกรอก Name=Prayoch และ Age=50 กดปุ่ม ADDED และกด GO TO LIST
        // จะต้องเจอ Prayoch อายุ 50 ใน ListView ลำดับที่ 5

        onView(withId(R.id.editTextName)).perform(click());

        onView(withId(R.id.editTextName)).perform(replaceText("Prayoch"), closeSoftKeyboard());

        onView(withId(R.id.editTextAge)).perform(replaceText("50"), closeSoftKeyboard());

        onView(withId(R.id.buttonAdded)).perform(click());

        onView(withId(R.id.buttonGotoList)).perform(click());

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(4, R.id.textName))
                .check(matches(withText("Prayoch")));

        onView(withRecyclerView(R.id.list)
                .atPositionOnView(4, R.id.textAge))
                .check(matches(withText("50")));

    }
}
