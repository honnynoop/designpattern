public class TextBuilder extends Builder {
    private String buffer = "";                           // 이 필드에 문서를 구축한다
    public void makeTitle(String title) {                   // 일반 텍스트의 타이틀
        buffer += "==============================\n";      // 장식선
        buffer += "『" + title + "』\n";                   // 『』붙은 타이틀
        buffer += "\n";                                 // 빈행
    }
    public void makeString(String str) {                    // 일반 텍스트에서의 문자열
        buffer += '■' + str + "\n";                       // ■붙은 문자열
        buffer += "\n";                                  // 빈행
    }
    public void makeItems(String[] items) {                 // 일반 텍스트에서의 개별항목
        for (int i = 0; i < items.length; i++) {
            buffer += " ㆍ" + items[i] + "\n";             // ㆍ붙은 항목
        }
        buffer += "\n";                                  // 빈행
    }
    public void close() {                                  // 문서의 완성
        buffer += "==============================\n";           // 장식선
    }
    public String getResult() {                             // 완성한 문서
        return buffer;
    }
}
