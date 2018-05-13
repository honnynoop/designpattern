public class PrinterProxy implements Printable {
    private String name;            // 이름
    private Printable real;           // 「본인」                 
    private String className;       // 「본인」의 클래스명    
    public PrinterProxy(String name, String className) {      // 생성자     
        this.name = name;
        this.className = className;                                                 
    }
    public synchronized void setPrinterName(String name) {  // 이름 설정
        if (real != null) {
            real.setPrinterName(name);  // 「본인」에게도 설정한다
        }
        this.name = name;
    }
    public String getPrinterName() {    // 이름 취득
        return name;
    }
    public void print(String string) {  // 표시
        realize();
        real.print(string);
    }
    private synchronized void realize() {   // 「본인」을 생성
        if (real == null) {
            try {                                                                       
                real = (Printable)Class.forName(className).newInstance();               
                real.setPrinterName(name);                                              
            } catch (ClassNotFoundException e) {                                        
                System.err.println("클래스 " + className + " 가 발견되지 않습니다.");      
            } catch (Exception e) {                                                     
                e.printStackTrace();                                                    
            }                                                                           
        }
    }
}
