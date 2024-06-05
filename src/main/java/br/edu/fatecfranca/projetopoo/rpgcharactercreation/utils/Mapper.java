package br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.GlobalConstants.ATTRIBUTE_MODIFIER_MAP;

public class Mapper {
  public static <K, V> Map<K, V> zipToMap(List<K> keys, List<V> values) {
    return IntStream.range(0, keys.size()).boxed()
      .collect(Collectors.toMap(keys::get, values::get));
  }

  public static <E extends Enum<E>> Map<String, Integer> getMapOfAttributes(Map<E, Integer> enumMap) {
    return enumMap.entrySet().stream()
      .collect(Collectors.toMap(entry -> entry.getKey().name(), Map.Entry::getValue));
  }

  public static Map<String, Integer> getMapOfAttributeModifier(Map<AttributeEnum, Integer> enumMap) {
    var values = enumMap.values();
    return enumMap.entrySet().stream()
      .collect(Collectors.toMap(entry -> entry.getKey().name(), entry -> ATTRIBUTE_MODIFIER_MAP.get(entry.getValue())));
  }
}
