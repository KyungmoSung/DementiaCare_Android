package com.example.sung.dementiacare.information;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sung.dementiacare.R;
import com.github.barteksc.pdfviewer.PDFView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sung on 2017. 9. 3..
 */

public class PdfViewerActivity extends AppCompatActivity {

    final int INFORMATION_DEMENTIA_RESOURCE_ID[] = {R.array.arrays_dementia_pdf_pages_1, R.array.arrays_dementia_pdf_pages_2, R.array.arrays_dementia_pdf_pages_3, R.array.arrays_dementia_pdf_pages_4};
    final int INFORMATION_CARE_RESOURCE_ID[] = {R.array.arrays_care_pdf_pages_1, R.array.arrays_care_pdf_pages_2, R.array.arrays_care_pdf_pages_3, R.array.arrays_care_pdf_pages_4};
    final int INFORMATION_RESOURCE_ID[][] = {INFORMATION_DEMENTIA_RESOURCE_ID, INFORMATION_CARE_RESOURCE_ID};

    @BindView(R.id.pdfView)
    PDFView pdfView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int menuIndex = intent.getIntExtra("menu_index", 0);
        int mainIndex = intent.getIntExtra("main_index", 0);
        int subIndex = intent.getIntExtra("sub_index", 0);

        String[][] pdfArray = getArrayFromResource(INFORMATION_RESOURCE_ID[menuIndex][mainIndex]);
        String pages = pdfArray[subIndex][1];
        String pdf = pdfArray[subIndex][0];

        Log.e("pdf", (menuIndex + 1) + "-" + (mainIndex + 1) + "-" + (subIndex + 1) + " / " + pdf + " / " + pages);

        int division = pages.indexOf("~");
        int begin = Integer.parseInt(pages.substring(0, division));
        int end = Integer.parseInt(pages.substring(division + 1));

        pdfView.fromAsset(pdf)
                .pages(pagesRange(begin, end))
                .load();
    }

    public int[] pagesRange(int begin, int end) {
        int[] pages = new int[end - begin + 1];
        int j = 0;
        for (int i = begin; i <= end; i++) {
            pages[j] = i;
            j++;
        }
        return pages;
    }

    public String[][] getArrayFromResource(int resourceId) {
        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(resourceId);

        int n = ta.length();
        String[][] array = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            if (id > 0) {
                array[i] = res.getStringArray(id);
                Log.e("array", array[i][0] + ", " + array[i][1]);
            } else {
                Log.e("getStringArray", "Not found");
            }
        }
        ta.recycle();
        return array;
    }
}