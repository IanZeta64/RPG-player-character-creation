package br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;

import java.util.List;
import java.util.Map;

public class GlobalConstants {


  public static final String METHOD_ARGUMENT_NOT_VALID_ERROR_MESSAGE = "Invalid Field: '%s'. Cause: '%s'.";
  public static final String ENTITY_NOT_FOUNDED_EXCEPTION_MESSAGE = "%s not founded by id %s";
  public static final String ENTITY_DUPLICATED_EXCEPTION_MESSAGE = "%s already exists, cannot proceed";
  public static final String RACE = "race";
  public static final String COMBAT_CLASS = "combat class";
  public static final String PLAYER_CHARACTER = "player character";
  public static Integer MAX_LEVEL = 20;
  public static final List<AttributeEnum> ATTRIBUTES_TYPES = List.of(AttributeEnum.STRENGTH,AttributeEnum.DEXTERITY, AttributeEnum.CONSTITUTION, AttributeEnum.INTELLIGENCE, AttributeEnum.WISDOM, AttributeEnum.CHARISMA);

  public static final Map<Integer, Integer> PROFICIENCY_VALUE_MAP = Map.ofEntries(
    Map.entry(1, 2), Map.entry(2, 2), Map.entry(3, 2), Map.entry(4, 2),
    Map.entry(5, 3), Map.entry(6, 3), Map.entry(7, 3), Map.entry(8, 3),
    Map.entry(9, 4), Map.entry(10, 4), Map.entry(11, 4), Map.entry(12, 4),
    Map.entry(13, 5), Map.entry(14, 5), Map.entry(15, 5), Map.entry(16, 5),
    Map.entry(17, 6), Map.entry(18, 6), Map.entry(19, 6), Map.entry(20, 6)
  );
  public static final Map<Integer, Integer> ATTRIBUTE_MODIFIER_MAP = Map.ofEntries(
    Map.entry(1, -5),
    Map.entry(2, -4), Map.entry(3, -4),
    Map.entry(4, -3), Map.entry(5, -3),
    Map.entry(6, -2), Map.entry(7, -2),
    Map.entry(8, -1), Map.entry(9, -1),
    Map.entry(10, 0), Map.entry(11, 0),
    Map.entry(12, 1), Map.entry(13, 1),
    Map.entry(14, 2), Map.entry(15, 2),
    Map.entry(16, 3), Map.entry(17, 3),
    Map.entry(18, 4), Map.entry(19, 4),
    Map.entry(20, 5)
  );
}
