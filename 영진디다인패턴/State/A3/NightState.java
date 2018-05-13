public class NightState implements State {
    private static NightState singleton = new NightState();
    private NightState() {                              // 생성자는 private
    }
    public static State getInstance() {                 // 유일한 인스턴스를 얻는다
        return singleton;
    }
    public void doClock(Context context, int hour) {    // 시간설정
        if (12 <= hour && hour < 13) {
            context.changeState(NoonState.getInstance());
        } else if (9 <= hour && hour < 17) {                   
            context.changeState(NoonState.getInstance());       
        }                                                       
    }
    public void doUse(Context context) {                // 금고사용
        context.callSecurityCenter("비상:야간 금고사용!");
    }
    public void doAlarm(Context context) {              // 비상벨
        context.callSecurityCenter("비상벨(야간)");
    }
    public void doPhone(Context context) {              // 일반통화
        context.recordLog("야간 일반통화");
    }
    public String toString() {                          // 문자열 표현
        return "[야간]";
    }
}
