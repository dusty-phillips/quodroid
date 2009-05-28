package ca.archlinux;

import android.test.ActivityInstrumentationTestCase;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class ca.archlinux.QuodroidTest \
 * ca.archlinux.tests/android.test.InstrumentationTestRunner
 */
public class QuodroidTest extends ActivityInstrumentationTestCase<Quodroid> {

    public QuodroidTest() {
        super("ca.archlinux", Quodroid.class);
    }

}
