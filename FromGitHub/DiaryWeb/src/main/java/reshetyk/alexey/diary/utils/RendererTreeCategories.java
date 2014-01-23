/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reshetyk.alexey.diary.utils;

import reshetyk.alexey.diary.domain.DiaryCategory;
import reshetyk.alexey.diary.service.DiaryCategoryService;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Alexey
 */
public class RendererTreeCategories {

    private final DiaryCategoryService diaryCategoryService;
    private StringBuilder out;
    private DiaryCategory activeCateg;
    private String linkContextPath ;

    public RendererTreeCategories(DiaryCategoryService diaryCategoryService) {
        this.diaryCategoryService = diaryCategoryService;
    }

    public String toHtmlList(List<DiaryCategory> listCateg, String linkContextPath) {
        this.linkContextPath = linkContextPath;
        out = new StringBuilder();
        toHtmlList(listCateg, 0);
        return out.toString();
    }

    private void toHtmlList(List<DiaryCategory> listCateg, int indent) {

        for (DiaryCategory diaryCategory : listCateg) {
            char[] blanks = new char[indent * 2];
            Arrays.fill(blanks, ' ');
            out.append(blanks)
                    .append("<li>")
                    .append("<a href='").append(linkContextPath).append("/diaryCategory/id/").append(diaryCategory.getId()).append("'>")
                    .append(diaryCategory.getName())
                    .append("</a>");

            List<DiaryCategory> children = diaryCategoryService.getChildren(diaryCategory);
            out.append("\n");
            if (children != null && children.size() > 0) {
                out.append("\n<ul>");
                toHtmlList(children, indent + 1);
                out.append("</ul>\n");
                out.append("</li>\n");
            }
            out.append("\n");
        }

    }

    public String toHtmlSelectList(List<DiaryCategory> listCateg, DiaryCategory activeCateg) {
        this.activeCateg = activeCateg;

        out = new StringBuilder();

        toHtmlSelectList(listCateg, 0);

        return out.toString();
    }

    private void toHtmlSelectList(List<DiaryCategory> listCateg, int indent) {

        for (DiaryCategory diaryCategoryIt : listCateg) {
            String[] blanks = new String[indent * 2];
            Arrays.fill(blanks, "&nbsp;");
            String selected = "";
            if (activeCateg != null && activeCateg.getParent() != null && activeCateg.getParent().equals(diaryCategoryIt)) {
                selected = "selected";
            }

            out.append("<option ").append(selected).append(" value='").append(diaryCategoryIt.getId()).append("'>");
            out.append(arrayToString(blanks)).append(diaryCategoryIt.getName());
            out.append("</option>");

            List<DiaryCategory> children = diaryCategoryService.getChildren(diaryCategoryIt);
            out.append("\n");
            if (children != null && children.size() > 0) {
                toHtmlSelectList(children, indent + 1);
            }
            out.append("\n");
        }

    }

    private String arrayToString(String[] arrStr) {
        StringBuilder res = new StringBuilder();
        for (String str : arrStr) {
            res.append(str);
        }
        return res.toString();
    }
}
