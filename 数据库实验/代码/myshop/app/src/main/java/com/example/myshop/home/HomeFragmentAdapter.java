package com.example.myshop.home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.R;
import com.example.myshop.base.GoodInfoActivity;
import com.example.myshop.bean.ResultBeanData;
import com.example.myshop.bean.productBean;
import com.example.myshop.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.myshop.utils.constants.BASE_IMAGE_URL;

public class HomeFragmentAdapter extends RecyclerView.Adapter {
    private static final int BANNER = 0;
    private static final int SECKILL = 1;
    private static final int RECOMMEND = 2;
    private static final int HOT = 3;
    private static final String PRODUCTBEAN = "productBean";
    private  LayoutInflater mLayoutInflater;
    //当前类型
    private int currentType = BANNER;
    private Context context;
    private ResultBeanData.DetailsBean resultBeanData;
    public HomeFragmentAdapter(Context basecontext, ResultBeanData.DetailsBean resultBeanData) {
        this.context = basecontext;
        this.resultBeanData = resultBeanData;
        mLayoutInflater = LayoutInflater.from(basecontext);
    }

    /**
     * 相当于getview,创建viewholder;
     * @param parent
     * @param viewType 当前的类型;
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(context, mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }
        else if (viewType == SECKILL) {
            return new SeckillViewHolder(context, mLayoutInflater.inflate(R.layout.seckill_item, null));
        }
        else if(viewType == RECOMMEND) {
            return new RecommendViewHolder(context, mLayoutInflater.inflate(R.layout.recommend_item, null));
        }
        else if(viewType == HOT) {
            return new HotViewHolder(context, mLayoutInflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    /**\
     * 相当于getview中的绑定数据;
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData(resultBeanData.getBannerproductList());
        }
        else if(getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(resultBeanData.getSeckillproductList());
        }
        else if(getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(resultBeanData.getRecommendproductList());
        }
        else if(getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;

            hotViewHolder.setData(resultBeanData.getHotproduct().getHotproductList());
        }
    }

    /**
     * 得到类型;
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    /**
     * 总共有多少个ITEM
     * @return
     */
    @Override
    public int getItemCount() {
        return 4;
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView tv_more_hot;
        private GridView gv_hot;
        private HotGridViewAdapter hotGridViewAdapter;
        public HotViewHolder(final Context context, @NonNull View itemView) {
            super(itemView);
            this.context = context;
            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);

        }

        public void setData(final List<ResultBeanData.DetailsBean.HotproductBean.HotproductListBean> hotproductList) {
            hotGridViewAdapter = new HotGridViewAdapter(context, hotproductList);
            gv_hot.setAdapter(hotGridViewAdapter);
            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "position=="+position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.DetailsBean.HotproductBean.HotproductListBean hotproductListBean = hotproductList.get(position);
                    productBean product = new productBean();
                    product.setPrice(hotproductListBean.getPrice());
                    product.setFile_name(hotproductListBean.getFileName());
                    product.setName(hotproductListBean.getName());
                    product.setId(hotproductListBean.getId());
                    startGoodsInfoActivity(product);
                }
            });
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private RecommendGridViewAdapter recommendGridViewAdapter;
        public RecommendViewHolder(final Context context, View itemView) {
            super(itemView);
            this.context = context;
            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_recommend);

        }

        public void setData(final List<ResultBeanData.DetailsBean.RecommendproductListBean> recommendproductList) {
            recommendGridViewAdapter = new RecommendGridViewAdapter(context, recommendproductList);
            gv_recommend.setAdapter(recommendGridViewAdapter);
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "position=="+position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.DetailsBean.RecommendproductListBean recommendproductListBean = recommendproductList.get(position);
                    productBean product = new productBean();
                    product.setPrice(recommendproductListBean.getPrice());
                    product.setFile_name(recommendproductListBean.getFileName());
                    product.setName(recommendproductListBean.getName());
                    product.setId(recommendproductListBean.getId());
                    startGoodsInfoActivity(product);
                }
            });
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private Banner banner;
        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.mContext = context;
            this.banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(final List<ResultBeanData.DetailsBean.BannerproductListBean> bannerproductListBeans) {
//设置banner的数据;
            List<String> imagesUrl = new ArrayList<>();
            for(int i = 0; i < bannerproductListBeans.size(); i++) {
                String imageUrl = bannerproductListBeans.get(i).getFileName();
                imagesUrl.add(BASE_IMAGE_URL + imageUrl);
            }
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(imagesUrl);
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(context, "position="+position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.DetailsBean.BannerproductListBean bannerproductListBean = bannerproductListBeans.get(position);
                    productBean product = new productBean();
                    product.setPrice(bannerproductListBean.getPrice());
                    product.setFile_name(bannerproductListBean.getFileName());
                    product.setName(bannerproductListBean.getName());
                    product.setId(bannerproductListBean.getId());
                    startGoodsInfoActivity(product);
                }
            });
            banner.start();
        }
    }

    /**
     * 启动商品详情界面
     * @param product
     */
    private void startGoodsInfoActivity(productBean product) {
        Intent intent = new Intent(context, GoodInfoActivity.class);
        intent.putExtra(PRODUCTBEAN, product);
        context.startActivity(intent);
    }
    /**
     * 相差多少时间-毫秒
     */

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        private SeckillRecyclerViewAdapter seckillRecyclerViewAdapter;

        private long dt = 46000;
        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                dt = dt - 1000;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time =  simpleDateFormat.format(new Date(dt));
                tv_time_seckill.setText(time);
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                if(dt <= 0) {
//                    把消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };
        public SeckillViewHolder(Context context, @NonNull View itemView) {
            super(itemView);
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);
            this.mContext = context;
        }

        public void setData(final List<ResultBeanData.DetailsBean.SeckillproductListBean> seckillproductList) {
            seckillRecyclerViewAdapter = new SeckillRecyclerViewAdapter(context, seckillproductList);
            rv_seckill.setAdapter(seckillRecyclerViewAdapter);
            rv_seckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            seckillRecyclerViewAdapter.setOnSeckillRecyclerView(new SeckillRecyclerViewAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(context, "秒杀"+position, Toast.LENGTH_SHORT).show();
                    ResultBeanData.DetailsBean.SeckillproductListBean seckillproductListBean = seckillproductList.get(position);
                    productBean product = new productBean();
                    product.setPrice(seckillproductListBean.getPrice());
                    product.setFile_name(seckillproductListBean.getFileName());
                    product.setName(seckillproductListBean.getName());
                    product.setId(seckillproductListBean.getId());
                    startGoodsInfoActivity(product);
                }
            });
            /**
             * 相差多少毫秒
             */
            handler.sendEmptyMessageDelayed(0,1000);
        }
    }
}
