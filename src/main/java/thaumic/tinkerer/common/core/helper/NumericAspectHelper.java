package thaumic.tinkerer.common.core.helper;

import java.util.ArrayList;

import thaumcraft.api.aspects.Aspect;

public class NumericAspectHelper {

    public static ArrayList<NumericAspectHelper> values = new ArrayList<>();
    private static int nextNum = 0;
    public int num;
    private Aspect aspect;

    NumericAspectHelper(Aspect aspect) {
        this.aspect = aspect;
        this.num = nextNum;
        nextNum++;
        values.add(this);
    }

    public static void init() {

        new NumericAspectHelper(Aspect.WATER);
        new NumericAspectHelper(Aspect.MAN);
        new NumericAspectHelper(Aspect.AIR);
        new NumericAspectHelper(Aspect.FLIGHT);
        new NumericAspectHelper(Aspect.FIRE);
        new NumericAspectHelper(Aspect.MAGIC);
        new NumericAspectHelper(Aspect.UNDEAD);
        new NumericAspectHelper(Aspect.FLESH);
        new NumericAspectHelper(Aspect.BEAST);
        new NumericAspectHelper(Aspect.POISON);
        new NumericAspectHelper(Aspect.EARTH);
        new NumericAspectHelper(Aspect.ELDRITCH);
        new NumericAspectHelper(Aspect.TRAVEL);
        new NumericAspectHelper(Aspect.METAL);
        new NumericAspectHelper(Aspect.SLIME);
        new NumericAspectHelper(Aspect.CLOTH);
        new NumericAspectHelper(Aspect.CROP);
    }

    public static int getNumber(Aspect aspect) {
        for (NumericAspectHelper e : NumericAspectHelper.values) {
            if (e.getAspect().equals(aspect)) {
                return e.num;
            }
        }

        return -1;
    }

    public static Aspect getAspect(int i) {
        return NumericAspectHelper.values.get(i).getAspect();
    }

    public Aspect getAspect() {
        return aspect;
    }
}
