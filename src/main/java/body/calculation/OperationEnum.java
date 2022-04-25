package body.calculation;

import java.util.Arrays;
import java.util.List;

public enum OperationEnum {
    ADDITION,//сложение
    SUBTRACTION,//вычитание
    MULTIPLICATION,//умножение
    DIVISION;//деление

    public static List<OperationEnum> getPlusMinus() {
        return Arrays.asList(ADDITION, SUBTRACTION);
    }

    public static List<OperationEnum> getMultiplicationDivision() {
        return Arrays.asList(MULTIPLICATION, DIVISION);
    }

    public static List<OperationEnum> getAllOperations() {
        return Arrays.asList(values());
    }
}

