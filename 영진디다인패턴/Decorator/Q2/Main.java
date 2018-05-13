public class Main {
    public static void main(String[] args) {
        MultiStringDisplay md = new MultiStringDisplay();
        md.add("좋은 아침입니다.");
        md.add("안녕하세요.");
        md.add("안녕히 주무세요. 내일 만나요.");
        md.show();

        Display d1 = new SideBorder(md, '#');
        d1.show();

        Display d2 = new FullBorder(md);
        d2.show();
    }
}
