/*
 * ValueToString.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dto.CheckboxesElement;
import be.ugent.justin.db.dto.Element;
import be.ugent.justin.db.dto.ElementVisitor;
import be.ugent.justin.db.dto.OptionsElement;

import java.util.Map;

/**
 * Visitor that converts a form value to a string to be stored in the database, depending on the type of the element
 * that the value belongs to.
 */
public class ValueToString extends ElementVisitor<String> {

    private final FormDeputy.PageData data;

    public ValueToString(FormDeputy.PageData data) {
        this.data = data;
    }

    @Override
    public String visitElement(Element element) {
        return null;
    }

    @Override
    public String visitQuestion(Element element) {
        return data.string == null ? null : data.string.get(element.getId());
    }

    @Override
    public String visitOptions(OptionsElement element) {
        // TODO 'other' only in visitRadio
        Integer option = data.number == null ? null : data.number.get(element.getId());
        if (option == null) {
            return null;
        } else if (option == 0) {
            return data.string.get(element.getId()); // Other
        } else {
            // TODO 'other' options needs a marker character in first position
            return option.toString();
        }
    }

    @Override
    public String visitCheckboxes(CheckboxesElement element) {
        if (data.check == null) {
            return null;
        } else {
            Map<Integer, Boolean> optionsChecked = data.check.get(element.getId());
            if (optionsChecked == null) {
                return null;
            } else {
                StringBuilder result = new StringBuilder();
                boolean hasOther = false;
                for (int key : optionsChecked.keySet()) {
                    if (key == 0) {
                        hasOther = true;
                    } else {
                        result.append(key).append(",");
                    }
                }
                result.deleteCharAt(result.length() - 1); // remove last comma
                if (hasOther && data.string != null) {
                    result.append(";").append(data.string.get(element.getId())); // Other
                }
                return result.toString();
            }
        }
    }
}
