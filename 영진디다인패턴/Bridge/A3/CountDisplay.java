public class CountDisplay extends Display {
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }
    public void multiDisplay(int times) {       // times회 반복해서 실행한다
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
