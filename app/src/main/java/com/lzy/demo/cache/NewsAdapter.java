/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lzy.demo.cache;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.lzy.demo.R;
import com.lzy.demo.WebActivity;
import com.lzy.demo.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/8/17
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class NewsAdapter extends BaseQuickAdapter<NewsModel.ContentList> {

    public NewsAdapter(List<NewsModel.ContentList> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final NewsModel.ContentList contentList) {
        baseViewHolder.setText(R.id.title, contentList.title)//
                .setText(R.id.desc, contentList.desc)//
                .setText(R.id.pubDate, contentList.pubDate)//
                .setText(R.id.source, contentList.source);

        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.runActivity(mContext, contentList.title, contentList.link);
            }
        });

        NineGridView nineGrid = baseViewHolder.getView(R.id.nineGrid);
        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        List<NewsModel.NewsImage> images = contentList.imageurls;
        if (images != null) {
            for (NewsModel.NewsImage image : images) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(image.url);
                info.setBigImageUrl(image.url);
                imageInfo.add(info);
            }
        }
        nineGrid.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));

        if (images != null && images.size() == 1) {
            nineGrid.setSingleImageRatio(images.get(0).width * 1.0f / images.get(0).height);
        }
    }
}
