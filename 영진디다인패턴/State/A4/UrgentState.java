public class UrgentState implements State {
    private static UrgentState singleton = new UrgentState();
    private UrgentState() {                                // 생성자는 private
    }
    public static State getInstance() {                 // 유일한 인스턴스를 얻는다
        return singleton;
    }
    public void doClock(Context context, int hour) {    // 시간설정  
        // 시간설정에서는 아무 처리를 하지 않는다               
    }                                                           
    public void doUse(Context context) {                // 금고사용
        context.callSecurityCenter("비상:비상사태 금고사용!");
    }
    public void doAlarm(Context context) {              // 비상벨
        context.callSecurityCenter("비상벨(비상사태)");
    }
    public void doPhone(Context context) {              // 일반통화
        context.callSecurityCenter("일반통화(비상사태)");
    }
    public String toString() {                          // 문자열 표현
        return "[비상사태]";
    }
}
