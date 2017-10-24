package lab08.kmitl.chayanon58070021.espresso;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;;
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

        // no input in each text field , press Added Button
        // result : found "Please Enter user info"

        ViewInteraction buttonAdded = onView(allOf(withId(R.id.buttonAdded), isDisplayed()));
        buttonAdded.check(matches(isDisplayed()));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("Please Enter user info"),
                        isDisplayed()));
        textView.check(matches(withText("Please Enter user info")));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton2.perform(click());

    }

    @Test
    public void testCase2() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("Please Enter user info"),
                        isDisplayed()));
        textView.check(matches(withText("Please Enter user info")));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        appCompatButton2.perform(click());


    }

    @Test
    public void testCase3() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textNotFound), withText("Not Found"), isDisplayed()));
        textView.check(matches(withText("Not Found")));
    }

    @Test
    public void testCase4() {
        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText2.perform(replaceText("Ying"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.message), withText("Please Enter user info"),
                        isDisplayed()));
        textView.check(matches(withText("Please Enter user info")));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        appCompatButton2.perform(click());
    }

    @Test
    public void testCase5() {

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText2.perform(replaceText("Ying"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText3.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textName = onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textName));
        textName.check(matches(withText("Ying")));

        ViewInteraction textAge = onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textAge));
        textAge.check(matches(withText("20")));


    }

    @Test
    public void testCase6() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText2.perform(replaceText("Ladarat"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText3.perform(replaceText("20"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textName = onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textName));
        textName.check(matches(withText("Ladarat")));

        ViewInteraction textAge = onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textAge));
        textAge.check(matches(withText("20")));


    }

    @Test
    public void testCase7() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText2.perform(replaceText("Somkait"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText3.perform(replaceText("80"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textName = onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textName));
        textName.check(matches(withText("Somkait")));

        ViewInteraction textAge = onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textAge));
        textAge.check(matches(withText("80")));
    }

    @Test
    public void testCase8() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editTExtName), isDisplayed()));
        appCompatEditText2.perform(replaceText("Prayoch"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editTextAge), isDisplayed()));
        appCompatEditText3.perform(replaceText("60"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textName = onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textName));
        textName.check(matches(withText("Prayoch")));

        ViewInteraction textAge = onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textAge));
        textAge.check(matches(withText("60")));
    }
}
