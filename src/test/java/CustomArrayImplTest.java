import org.junit.Assert;
import org.junit.Test;
import ru.sbrf.edu.CustomArrayImpl;

import java.util.ArrayList;
import java.util.Collection;

public class CustomArrayImplTest {

    @Test
    public void CustomArrayImplTest_size(){
        CustomArrayImpl<String> test = new CustomArrayImpl<>();

        Assert.assertEquals(0, test.size());
        Assert.assertTrue(test.add("Один"));

        Assert.assertEquals(1, test.size());

    }

    @Test
    public void CustomArrayImplTest_isEmpty() {
        CustomArrayImpl<Integer> test = new CustomArrayImpl<>();

        Assert.assertTrue(test.isEmpty());
    }

    @Test
    public void CustomArrayImplTest_add() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();

        Assert.assertTrue(test.add("Один"));
        Assert.assertTrue(test.add("Два"));
    }

    @Test
    public void CustomArrayImplTest_addAll() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        String[] testMassive = new String[]{
                "Один", "Два","Три"
        };

        Assert.assertTrue(test.addAll(testMassive));
    }

    @Test
    public void CustomArrayImplTest_addAllCollection() {
        CustomArrayImpl<Integer> test = new CustomArrayImpl<>(){ };
        test.add(1);
        test.add(5);
        test.add(11);

        Collection<Integer> testList = new ArrayList<>();
        testList.add(3);
        testList.add(7);
        testList.add(13);

        Assert.assertTrue(test.addAll(testList));
    }

    @Test
    public void CustomArrayImplTest_addAllIndex() {
        CustomArrayImpl<Integer> test = new CustomArrayImpl<>(){ };
        test.add(1);
        test.add(5);
        test.add(11);

        Collection<Integer> testList = new ArrayList<>();
        testList.add(3);
        testList.add(7);
        testList.add(13);

        Assert.assertTrue(test.addAll(1, testList.toArray()));
    }

    @Test
    public void CustomArrayImplTest_get() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        test.add("Один");
        test.add("Полтора");
        test.add("Два");

        Assert.assertEquals("Один", test.get(0));
    }

    @Test
    public void CustomArrayImplTest_set() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        test.add("Один");
        test.add("Полтора");
        test.add("Два");

        CustomArrayImpl<String> test2 = new CustomArrayImpl<>();
        test2.add("Один");
        test2.add("Два");
        test2.add("Три");

        test2.set(1,"Полтора");
        test2.set(2,"Два");

        Assert.assertArrayEquals(test.toArray(), test2.toArray());
    }

    @Test
    public void CustomArrayImplTest_removeIndex() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        test.add("Один");
        test.add("Полтора");
        test.add("Два");

        CustomArrayImpl<String> test2 = new CustomArrayImpl<>();
        test2.add("Один");
        test2.add("Два");

        test.remove(1);

        Assert.assertArrayEquals(test.toArray(), test2.toArray());

    }

    @Test
    public void CustomArrayImplTest_removeItem() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        test.add("Один");
        test.add("Полтора");
        test.add("Два");

        CustomArrayImpl<String> test2 = new CustomArrayImpl<>();
        test2.add("Один");
        test2.add("Два");

        test.remove("Полтора");

        Assert.assertArrayEquals(test.toArray(), test2.toArray());
    }

    @Test
    public void CustomArrayImplTest_contains() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        test.add("Один");
        test.add("Полтора");
        test.add("Два");

        Assert.assertFalse(test.contains("Olby"));
        Assert.assertTrue(test.contains("Один"));
    }

    @Test
    public void CustomArrayImplTest_indexOf() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        test.add("Один");
        test.add("Полтора");
        test.add("Два");

        Assert.assertEquals(0,test.indexOf("Один"));
    }

    @Test
    public void CustomArrayImplTest_ensureCapacity() {
        CustomArrayImpl<Integer> test = new CustomArrayImpl<>() {};
        test.add(1);
        test.add(2);
        test.add(3);

        int capacity = test.getCapacity();
        test.ensureCapacity(7);

        Assert.assertNotEquals(capacity, test.getCapacity());
    }

    @Test
    public void CustomArrayImplTest_getCapacity() {
        CustomArrayImpl<Integer> test = new CustomArrayImpl<>() {};
        test.add(1);
        test.add(2);
        test.add(3);

        Assert.assertEquals(5, test.getCapacity());
    }

    @Test
    public void CustomArrayImplTest_reverse() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        test.add("Один");
        test.add("Полтора");
        test.add("Два");
        test.add("Три");

        CustomArrayImpl<String> test2 = new CustomArrayImpl<>();
        test2.add("Три");
        test2.add("Два");
        test2.add("Полтора");
        test2.add("Один");

        test.reverse();

        Assert.assertArrayEquals(test.toArray(), test2.toArray());
    }
    @Test
    public void CustomArrayImplTest_toString() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        test.add("Один");
        test.add("Полтора");
        test.add("Два");

        String testString = "[ Один Полтора Два ]";

        Assert.assertEquals(testString, test.toString());

    }

    @Test
    public void CustomArrayImplTest_toArray() {
        CustomArrayImpl<String> test = new CustomArrayImpl<>();
        test.add("Один");
        test.add("Полтора");
        test.add("Два");

        String[] testArray = new String[3];
        testArray[0] = "Один";
        testArray[1] = "Полтора";
        testArray[2] = "Два";

        Assert.assertArrayEquals(testArray, test.toArray());

    }

}
