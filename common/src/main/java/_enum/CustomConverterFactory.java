package _enum;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhenhua zhang
 * @data 2019/11/29
 */
public class CustomConverterFactory implements ConverterFactory<Integer,BaseEnum> {

    private static final Map<Class, Converter> converterMap = new ConcurrentHashMap<>();

    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        Converter<Integer, T> converter = converterMap.get(targetType);
        if(converter == null){
            converter = new CodeConverterToEnum<>(targetType);
            converterMap.put(targetType, converter);
        }
        return converter;
    }

    class CodeConverterToEnum<T extends BaseEnum> implements Converter<Integer, T> {
        private Map<Integer, T> enumMap = new ConcurrentHashMap<>();

        public CodeConverterToEnum(Class<T> enumType) {
            T[] enums = enumType.getEnumConstants();
            for(T e : enums) {
                enumMap.put(e.getCode(), e);
            }
        }
        @Override
        public T convert(Integer source) {
            return enumMap.get(source);
        }
    }
}
