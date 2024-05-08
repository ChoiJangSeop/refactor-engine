package skkuse.team7.refactorengine.service;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skkuse.team7.refactorengine.domain.FixedCode;

@Service
@RequiredArgsConstructor
public class IterateGetSizeRefactor implements BaseRefactor {

    @Override
    public FixedCode doFix(String codeText) {
        boolean isDetected = false;
        int classStartIndex = 0;

        String buggyPart = "";
        String fixedPart = "";
        String fixedCodeText = "";


        // 코드 분할
        String[] codes = codeText.split("\n");
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(codes));



        // 검출
        int buggyLine = -1;
        String arrayListVariableName = "";
        int lineSize = lines.size();

        for(int i=0; i<lineSize; i++) {
            String line = codes[i];
            if(line.contains("public class Buggy")) {
                classStartIndex = i;
                continue;
            }

            Pattern pattern = Pattern.compile("\\bArrayList\\s*<[^>]*>\\s+([a-zA-Z0-9_]+)\\s*=");

            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                arrayListVariableName = matcher.group(1);

                for(int j=i; j<codes.length; j++) {
                    String line2 = codes[j];
                    Pattern sizeMethodPattern = Pattern.compile("\\b" + arrayListVariableName + "\\.size\\(\\)");

                    Matcher sizeMethodMatcher = sizeMethodPattern.matcher(line2);
                    if (sizeMethodMatcher.find()) {
                        if (line2.contains("for") || line2.contains("while")) {
                            buggyLine = j;
                            buggyPart += (j+1) + ": " + line2;
                            isDetected = true;
                            break;
                        }
                    }
                }
            }
        }

        // 수정
        lines.set(classStartIndex, "public class Fixed {\n");
        if(isDetected) {
            int count = countLeadingSpaces(lines.get(buggyLine));

            lines.set(buggyLine, lines.get(buggyLine).replace(arrayListVariableName + ".size()", arrayListVariableName + "Size"));

            StringBuilder builder = new StringBuilder();
            for(int i=0; i<count; i++) {
                builder.append(' ');
            }
            builder.append("int " + arrayListVariableName + "Size = " + arrayListVariableName + ".size();\n");
            lines.add(buggyLine, builder.toString());

            // get fixed line
            fixedPart += (buggyLine+1) + ":" + lines.get(buggyLine);
            fixedPart += (buggyLine+2) + ":" + lines.get(buggyLine+1);
        }

        for (int i=0; i<lineSize; ++i) { fixedCodeText += lines.get(i) + "\n"; }

        // TODO implement get reduced CO2
        return FixedCode.of(RefactorType.ITER_GET_SIZE, fixedCodeText, buggyPart, fixedPart, 0.0);
    }

    public static int countLeadingSpaces(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
