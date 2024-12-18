/*
 * AdditionalContent.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Copyright © 2024 Kris Coolsaet (Universiteit Gent)
 *
 * This software is distributed under the MIT License - see files LICENSE and AUTHORS
 * in the top level project directory.
 */

package deputies;

import be.ugent.justin.db.dto.*;
import org.apache.commons.lang3.StringUtils;
import play.data.Form;
import play.twirl.api.Html;
import views.html.be.ugent.caagt.play.ext.Forms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Visitor that produces the HTML for additional content to an element that depends on the type of the element.
 */
public class AdditionalContent extends ElementVisitor<Html> {

    private final FormDeputy formDeputy;

    public AdditionalContent(FormDeputy formDeputy) {
        this.formDeputy = formDeputy;
    }

    private Form.Field stringField(QuestionElement element) {
        return Forms.field("string[" + element.getId() + "]", element.getAnswer());
    }

    private Form.Field numberField(QuestionElement element) {
        return Forms.field("number[" + element.getId() + "]", element.getAnswer());
    }

    @Override
    public Html visitElement(Element element) {
        return views.html.form.unknown.render(element, formDeputy);
    }

    @Override
    public Html visitInfo(InfoElement element) {
        return new Html("");
    }

    @Override
    public Html visitText(TextElement element) {
        return views.html.form.text.render(stringField(element), formDeputy);
    }

    @Override
    public Html visitRadio(RadioElement element) {
        // TODO 'other' options needs a marker character in first position
        if (element.hasOther()) {
            if (StringUtils.isNumeric(element.getAnswer())) {
                return views.html.form.radio_other.render(
                        numberField(element),
                        Forms.field("string[" + element.getId() + "]", ""),
                        element.getOptions(),
                        formDeputy);
            } else {
                return views.html.form.radio_other.render(
                        Forms.field("number[" + element.getId() + "]", "0"),
                        stringField(element),
                        element.getOptions(),
                        formDeputy);
            }
        } else {
            return views.html.form.radio.render(numberField(element), element.getOptions(), formDeputy);
        }
    }

    @Override
    public Html visitDate(DateElement element) {
        return views.html.form.date.render(stringField(element), formDeputy);
    }

    @Override
    public Html visitTextArea(TextAreaElement element) {
        return views.html.form.text_area.render(stringField(element), formDeputy);
    }

    private Set<Integer> getCheckedOptions(String answer) {
        Set<Integer> result = new HashSet<>();
        if (answer != null) {
            int index = answer.indexOf(';');
            if (index >= 0) {
                answer = answer.substring(0, index);
            }
            for (String part : answer.split(",")) {
                result.add(Integer.parseInt(part));
            }
        }
        return result;
    }

    public record FieldWithText(Form.Field field, String text) {
    }

    @Override
    public Html visitCheckboxes(CheckboxesElement element) {
        String answer = element.getAnswer();
        Set<Integer> checkedSet = getCheckedOptions(answer);
        List<FieldWithText> fields = element.getOptions().stream().map(option ->
                new FieldWithText(
                        Forms.field(
                                "check[" + element.getId() + "][" + option.id() + "]",
                                checkedSet.contains(option.id()) ? "true" : ""
                        ),
                        option.text()
                )
        ).toList();
        if (element.hasOther()) {
            String checkAnswer = "false";
            String stringAnswer = "";
            if (answer != null) {
                int index = answer.indexOf(';');
                if(index >= 0) {
                    checkAnswer = "true";
                    stringAnswer = answer.substring(index + 1);
                }
            }
            return views.html.form.checkboxes_other.render(
                    fields,
                    Forms.field("check[" + element.getId() + "][0]", checkAnswer),
                    Forms.field("string[" + element.getId() + "]", stringAnswer),
                    formDeputy
            );
        } else {
            return views.html.form.checkboxes.render(fields, formDeputy);
        }
    }

    @Override
    public Html visitSelect(SelectElement element) {
        return views.html.form.select.render(numberField(element), element.getOptions(), formDeputy);
    }

    @Override
    public Html visitButtons(ButtonsElement element) {
        return views.html.form.buttons.render(numberField(element), element.getOptions(), formDeputy);
    }
}
