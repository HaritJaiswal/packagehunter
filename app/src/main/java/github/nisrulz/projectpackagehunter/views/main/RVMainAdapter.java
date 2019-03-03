/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github.nisrulz.projectpackagehunter.views.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import github.nisrulz.packagehunter.PackageHunter;
import github.nisrulz.packagehunter.PkgInfo;
import github.nisrulz.projectpackagehunter.R;
import java.util.List;

public class RVMainAdapter extends RecyclerView.Adapter<RVMainAdapter.ItemViewHolder> {

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        final ImageView icon;

        final TextView txt_appname;

        final TextView txt_pkgname;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txt_appname = itemView.findViewById(R.id.txtvw_appname);
            txt_pkgname = itemView.findViewById(R.id.txtvw_pkgname);
            icon = itemView.findViewById(R.id.imgvw_icn);
        }
    }

    private List<PkgInfo> dataList;

    private final PackageHunter packageHunter;

    public RVMainAdapter(Context context, List<PkgInfo> dataList) {
        packageHunter = new PackageHunter(context);
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.txt_appname.setText(dataList.get(position).getAppName());
        holder.txt_pkgname.setText(dataList.get(position).getPackageName());

        Drawable icon = packageHunter.getIconForPkg(dataList.get(position).getPackageName());
        holder.icon.setImageDrawable(icon);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_main_item, parent, false);

        return new ItemViewHolder(view);
    }

    public void updateWithNewListData(List<PkgInfo> newDataList) {
        dataList.clear();
        dataList = null;
        dataList = newDataList;
        notifyDataSetChanged();
    }
}
