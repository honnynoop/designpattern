package drawer;

import command.Command;
import java.awt.Color;

public class ColorCommand implements Command {
    // 그림 그리기 대상
    protected Drawable drawable;
    // 그림 그리기 색
    private Color color;
    // 생성자
    public ColorCommand(Drawable drawable, Color color) {
        this.drawable = drawable;
        this.color = color;
    }
    // 실행
    public void execute() {
        drawable.setColor(color);
    }
}
