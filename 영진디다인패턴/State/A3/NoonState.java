public class NoonState implements State {
    private static NoonState singleton = new NoonState();
    private NoonState() {                            // 생성자는 private
    }
    public static State getInstance() {                 // 유일한 인스턴스를 얻는다
        return singleton;
    }
    public void doClock(Context context, int hour) {    // 시간설정     
        if (hour < 9 || 17 <= hour) {
            context.changeState(NightState.getInstance());
        } else if (9 <= hour && hour < 12 || 13 <= hour && hour < 17) {
            context.changeState(DayState.getInstance());
        }
    }
    public void doUse(Context context) {                // 금고사용
        context.callSecurityCenter("비상:점심시간 금고사용!");
    }
    public void doAlarm(Context context) {              // 비상벨
        context.callSecurityCenter("비상벨(점심시간)");
    }
    public void doPhone(Context context) {              // 일반통화
        context.recordLog("점심시간 통화녹음");
    }
    public String toString() {                          // 문자열 표현
        return "[점심시간]";
    }
}
