package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected List<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();
        JsonObject[] jsonList = new JsonObject[exams.size()];
        for (int i = 0; i < exams.size(); i++) {
            Tuple<String, Integer> exam = exams.get(i);
            jsonList[i] = new JsonObject(
                    new JsonPair("course", new JsonString(exam.key)),
                    new JsonPair("mark", new JsonNumber(exam.value)),
                    new JsonPair("passed", new JsonBoolean(exam.value >= 3))
            );
        }
        jsonObject.add(
                new JsonPair("exams", new JsonArray(jsonList))
        );
        return jsonObject;
    }
}