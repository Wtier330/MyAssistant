package com.example.myapplication.fragment;

import static com.example.myapplication.constants.Fragmentconstants.SECTION_PIC;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.bean.ArtistPalett;
import com.example.myapplication.database.ArtistPalettSqilteHelper;
import com.example.myapplication.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author WTL
 * @date 2023/09/13
 * 随机生成颜色，并进行标记处理
 */
public class ArtistPalettPicture_Fragment extends Fragment {
    private View view;
    private ListView rlv_artist;
    private List<ArtistPalett> artistPalett = new ArrayList<>();
    private Button bt_artist_reggenerate;
    private EditText etArtistpalettPicInputcount;
    private List<String> colors = new ArrayList<>();
    private List<Integer> generatedColors = new ArrayList<>();
    private ColorAdapter colorAdapter = new ColorAdapter();
    private ClipboardManager clipboardManager;
    ArtistPalettSqilteHelper palettSqilteHelper;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public static ArtistPalettPicture_Fragment newInstance(String sectionNumber) {
        ArtistPalettPicture_Fragment fragment = new ArtistPalettPicture_Fragment();
        Bundle args = new Bundle();
        args.putString(SECTION_PIC, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.artistpalett_picture_fragment, container, false);
        DBinit();
        initView();
        initEvent();
        return view;
    }

    private void DBinit() {
        palettSqilteHelper = new ArtistPalettSqilteHelper(getActivity());
    }


    private void initEvent() {
        bt_artist_reggenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int input = 0;
                colors.clear();

                if (!TextUtils.isEmpty(etArtistpalettPicInputcount.getText().toString())) {
                    input = Integer.parseInt(etArtistpalettPicInputcount.getText().toString());
                }

                /**
                 * 生成随机颜色值
                 * */
                if (input != 0 && input <= 16581375) {
                    randomColor(input);
                } else if (input > 16581375) {
                    ToastUtil.toastShort(getActivity(), "请输入一个合理的范围");
                    etArtistpalettPicInputcount.setText("");
                } else {
                    randomColor(10);
                }
                colorAdapter.notifyDataSetChanged();
            }
        });
        rlv_artist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        rlv_artist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
                return true;
            }
        });

    }

    private void showPopupMenu(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.color_listmenu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_star:
                    // 处理收藏操作
                    final EditText editText = new EditText(getActivity());
                    editText.setSingleLine();
                    editText.setHint("标签");
                    editText.requestFocus();
                    editText.setFocusable(true);
                    AlertDialog.Builder inputDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("设置一个好记的名字吧！~")
                            .setView(editText)
                            .setPositiveButton("确定",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //TODO 收藏数据库存储
                                            String content = editText.getText().toString();
                                            palettSqilteHelper.insertDataByPic(colors.get(position), content);
                                            ToastUtil.toastShort(getActivity(), colors.get(position) + "收藏成功" + "," + content);
                                        }
                                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    inputDialog.create().show();


                    return true;
                case R.id.action_delete:
                    // 处理删除操作
                    colors.remove(position);
                    colorAdapter.notifyDataSetChanged();
                    return true;
                case R.id.action_copy:
                    // 处理复制操作
                    clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("label", colors.get(position));
                    clipboardManager.setPrimaryClip(clipData);
                    ToastUtil.toastShort(getActivity(), colors.get(position) + "已经复制到剪切板");
                    return true;
                default:
                    return false;
            }
        });

        popupMenu.show();
    }

    private void initView() {
        rlv_artist = view.findViewById(R.id.rlv_artist);
        rlv_artist.setAdapter(colorAdapter);

        bt_artist_reggenerate = view.findViewById(R.id.bt_artist_reggenerate);
        etArtistpalettPicInputcount = view.findViewById(R.id.et_artistpalett_pic_Inputcount);
        registerForContextMenu(rlv_artist);// 注册上下文菜单

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.color_listmenu, menu); // 创建菜单布局
    }

    /*
     * 该方法用随机数进行生成不重复的颜色并存入数组
     * */
    private void randomColor(int numberOfColorsToGenerate) {
        Random random = new Random();

        for (int i = 0; i < numberOfColorsToGenerate; i++) {
            int red, green, blue;
            int rgb;

            // 生成不重复的颜色值
            do {
                red = random.nextInt(256);
                green = random.nextInt(256);
                blue = random.nextInt(256);
                rgb = (red << 16) | (green << 8) | blue;
            } while (artistPalett.contains(rgb));

            generatedColors.add(rgb);

            // 转换为十六进制表示
            String hexColor = String.format("#%06X", rgb);
            colors.add(hexColor);
            System.out.println("随机生成的颜色值 " + (i + 1) + ": " + hexColor);
        }
    }

    class ColorAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return colors.size();
        }

        @Override
        public Object getItem(int position) {
            return colors.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(getActivity(), R.layout.color_drew_list, null);
            View colorshow = v.findViewById(R.id.color_view);
            TextView tv_hex = v.findViewById(R.id.tv_color_colorhex);

            tv_hex.setText(colors.get(position));
            colorshow.setBackgroundColor(Color.parseColor(colors.get(position)));
            return v;
        }
    }


}