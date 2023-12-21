package componentes;

import java.awt.CardLayout;
import java.awt.Container;

public class EnumCardLayout extends CardLayout {
	
    @Override
    public void show(Container parent, String name) {
        super.show(parent, name);
    }

    // Sobrescribe el método show para aceptar el enum directamente
    public void show(Container parent, Enum<?> panelEnum) {
        super.show(parent, panelEnum.name());
    }
}
