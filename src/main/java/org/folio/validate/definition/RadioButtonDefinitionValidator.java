package org.folio.validate.definition;

import static org.folio.rest.jaxrs.model.CustomField.Type.RADIO_BUTTON;
import static org.folio.validate.definition.AllowedFieldsConstants.SELECT_ALLOWED_FIELDS;
import static org.folio.validate.definition.CustomDefinitionValidationUtil.notSupportRepeatable;
import static org.folio.validate.definition.CustomDefinitionValidationUtil.onlyHasAllowedFields;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.folio.rest.jaxrs.model.CustomField;

@Component
public class RadioButtonDefinitionValidator extends SelectableField implements Validatable {

  @Value("${custom.fields.definition.radio.button.option.size.max}")
  private int radioButtonOptionsSizeMax;

  @Override
  public void validateDefinition(CustomField fieldDefinition) {
    onlyHasAllowedFields(fieldDefinition, SELECT_ALLOWED_FIELDS);
    notSupportRepeatable(fieldDefinition);
    validateSelectFieldDefined(fieldDefinition);
    validateDefaults(fieldDefinition);
    validateSingleDefaultSize(fieldDefinition);
    validateOptions(fieldDefinition, radioButtonOptionsSizeMax);
    validateMultiSelectProperty(fieldDefinition, false);
  }

  @Override
  public boolean isApplicable(CustomField fieldDefinition) {
    return RADIO_BUTTON.equals(fieldDefinition.getType());
  }
}
