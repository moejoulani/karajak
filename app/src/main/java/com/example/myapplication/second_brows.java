package com.example.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class        second_brows extends AppCompatActivity {
       private String Category,Select2,Select3,Selec4,Selec5;
       private TextView important;
       private String[] Options;
           private Spinner spinnerSections ,spinnerCars,spinnerSeries,spinnerModel;
       TextView txt,oilno3;
       private Button search_btn;
       private ImageView searchbtnbtn;
       RelativeLayout popmarly;
       private ImageView backIcon;
       private EditText searchField;
       TextView selecSeries, spinnerforseries,selectmod,seleccar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_brows);
        ActionBar actionBar =getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        important =(TextView) findViewById(R.id.important);
        spinnerSections =findViewById(R.id.spinnerforsections);
        spinnerCars =findViewById(R.id.spinnerforcars);
        popmarly =(RelativeLayout) findViewById(R.id.popmarly);
        spinnerSeries =findViewById(R.id.spinnerforSeries);
        spinnerModel =findViewById(R.id.spinnerformodel);
        txt =(TextView) findViewById(R.id.selectcar);
        seleccar =(TextView) findViewById(R.id.selectcar);
        oilno3 =(TextView) findViewById(R.id.selectno3);
        selecSeries = (TextView) findViewById(R.id.selectSereis);
        selectmod = (TextView)findViewById(R.id.selectmod);
        searchField = (EditText) findViewById(R.id.searchfield);
        searchbtnbtn =(ImageView)findViewById(R.id.search_btnbtn);

        Bundle bundle =new Bundle();
        Category=getIntent().getExtras().get("Category").toString();
            important.setText(Category);



            //    Toast.makeText(this,"The Cat : "+Category,Toast.LENGTH_SHORT).show();

        if(Category.equals("ميكانيك"))
        {
           // Toast.makeText(this,"hooooooooo",Toast.LENGTH_SHORT).show();
            Options= new String[]{"ـــــــــ","البواجي و نظام التشغيل", "الاقشطة و البكارات", "المحرك","الكسكيت و المرابط","انابيب و برابيش","مكيف الهواء و الراديتر","نظام القير","الصنوبرصات","الستيرنج","البخاخات","الاكزوزت"};

        }
        else if(Category.equals("بودي"))
        {
            Options = new String []{ "ـــــــــ", "الابواب الامامية","الابواب الخلفية","الجناح الامامي","الجناح الخلفي","الزجاج الخلفي","الزجاج الامامي","مرشات الطمبون الامامي","المرشة الجانبية -السفلية","دعامة امامية","دعامة خلفية","فصالات ابواب","زجاج ابواب","سبويلر خلفي","طمبون خلفي","طمبون امامي","قفل ابواب","مرأة الجناح","يد ابواب خارجية"};
        }
        else if(Category.equals("كهرباء"))
        {
            Options = new String []{"ـــــــــ", "الأضوية الامامية" ,"الاضوية الخلفية","الغمازات","كشافات سفلية","ضوء البريك الخلفي","ضوء غرفة السيارة","كشافات ليد","لمبة ضوء امامي","لمبة ضوء خلفي","لمبة غماز","لمبة كشاف"};
        }
        else if(Category.equals("اطارات"))
        {
            Options = new String []{"ـــــــــ",  "سيارة ركاب", "4X4 دفع رباع", "شاحنات خفيفة"};

        }
        else if(Category.equals("اكسسوارات"))
        {
            Options = new String []{"ـــــــــ","جك رفع","حبل سحب","حساس وقوف","سلاسل ثلوج","شادر سيارة","طفاية حريق","قفل ستيرنج","كاميرا خلفية","كيبل اشتراك","مفاتيح سيارة","مفتاح جنط","منفاخ الاطارات"};

        }
        else if(Category.equals("زيوت"))
        {
            Options = new String []{"ـــــــــ",  "ADDINOL","ACDELCO","CASTROL","TOTAL","MEGUIN","PETROMIN","ROVER","QUAKER","LUCAS","KIXX","SHELL","LIQUI MOLY","JOPETROL","GULF"};

        }
         final String Cars []={"جميع السيارات","BMW","Mercedes","Audi","Toyota","Honda","Kia","Hyundai","Chevrolet","Dodge"};

//_________________________________________________________________________________________________________________________________________________________________________________________________
        //_________________________________________________________________________________________________________________________________________________________________________________________________
        //_________________________________________________________________________________________________________________________________________________________________________________________________
        //_________________________________________________________________________________________________________________________________________________________________________________________________


        final String [] BmwSeries ={"الفئة الثالثة","الفئة الرابعة","الفئة الخامسة","الفئة السادسة","الفئة السابعة","X3","X5","X6"};
        final String [] MercedsSeries ={"الفئة A","الفئة C سيدان","الفئة E سيدان","الفئة S سيدان","CLA","الفئة C كوبيه","الفئة E كوبيه","الفئة S كوبيه","GLC","GLA","G"};
        final String [] ToyotaSeries ={"لاند كروزر","كورولا","كراون","ميراي","راف فور ","فورتشنر ","هايلكس ","يارس ","كامري","افالون ","بريوس","برادو "};
        final String [] HuyndaiSeries ={"اكسنت","ألينترا","توسان","سوناتا","جينسس"};
        final String [] AudiSeries    ={"RS5","R8","S8","KIO","Q5"};
        final String [] HondaSeries   ={"أكورد","سيفيك","سيتي","بايول","اتش ار في","كروس تور","اوديسي"};
        final String [] ChevroletSeries ={"سبارك","أفيو","أوبترا","ابيكا","لومينا","كابرس","كورفيت"};
        final String [] carNull={""};
        final String [] OilNum ={"5W-30","5W-40","10W-40","0W-30","15W-40","0W-40","5W-20","0W-20","10W-30"};
        final String [] DodgeSeries           ={"افيجر","تشارجر","تشالنجر","داكوتا","رام","شادو","ماغنوم"};
        final String [] KiaSeries ={"اوبتيما","افيلا","اسيا","بيكانتو","بنجو","بيستا","جراند بيرد","ريو","سبورتاج","سيراتو","سيفيا","كوريس"};
        final String LoLYears [] ={"1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
//_________________________________________________________________________________________________________________________________________________________________________________________________
        //_________________________________________________________________________________________________________________________________________________________________________________________________
        //_________________________________________________________________________________________________________________________________________________________________________________________________
        //_________________________________________________________________________________________________________________________________________________________________________________________________
        //_________________________________________________________________________________________________________________________________________________________________________________________________
        //_________________________________________________________________________________________________________________________________________________________________________________________________
                if(Category.equals("اطارات")) {

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) popmarly.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.spinnerforsections);
                    selectmod.setVisibility(View.INVISIBLE);
                    selecSeries.setVisibility(View.INVISIBLE);
                    //spinnerSections.setVisibility(View.INVISIBLE);
                    spinnerModel.setVisibility(View.INVISIBLE);
                    spinnerSeries.setVisibility(View.INVISIBLE);
                    oilno3.setText("اختر الفئة :");
                    spinnerCars.setVisibility(View.INVISIBLE);
                    seleccar.setVisibility(View.INVISIBLE);

                    ArrayAdapter arrayAdapter =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Options);
                    spinnerSections.setAdapter(arrayAdapter);
                    spinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            ArrayAdapter aa =new ArrayAdapter(second_brows.this,android.R.layout.simple_spinner_dropdown_item,carNull);
                            spinnerCars.setAdapter(aa);
                            ArrayAdapter aa2 =new ArrayAdapter(second_brows.this,android.R.layout.simple_spinner_dropdown_item,carNull);
                            spinnerSeries.setAdapter(aa);
                            spinnerModel.setAdapter(aa);
                            Select2 = spinnerSections.getSelectedItem().toString();
                            Select3 = spinnerCars.getSelectedItem().toString();
                            Selec4 = spinnerSeries.getSelectedItem().toString();
                            Selec5 = spinnerModel.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
        else if(Category.equals("زيوت")  ) {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) popmarly.getLayoutParams();
                    params.addRule(RelativeLayout.BELOW, R.id.spinnerforcars);
            txt.setText("عيار الزيت :");
            oilno3.setText("نوع الزيت");
            selectmod.setVisibility(View.INVISIBLE);
            selecSeries.setVisibility(View.INVISIBLE);
            //spinnerSections.setVisibility(View.INVISIBLE);
            spinnerModel.setVisibility(View.INVISIBLE);
            spinnerSeries.setVisibility(View.INVISIBLE);

            ArrayAdapter arrayAdapter =new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Options);
            spinnerSections.setAdapter(arrayAdapter);
            spinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ArrayAdapter aa =new ArrayAdapter(second_brows.this,android.R.layout.simple_spinner_dropdown_item,carNull);
                    ArrayAdapter aa2 =new ArrayAdapter(second_brows.this,android.R.layout.simple_spinner_dropdown_item,OilNum);
                    spinnerCars.setAdapter(aa2);
                  //  ArrayAdapter aa2 =new ArrayAdapter(second_brows.this,android.R.layout.simple_spinner_dropdown_item,carNull);
                    spinnerSeries.setAdapter(aa);
                    spinnerModel.setAdapter(aa);
                    Select2 = spinnerSections.getSelectedItem().toString();
                    Select3 = spinnerCars.getSelectedItem().toString();
                    Selec4 = spinnerSeries.getSelectedItem().toString();
                    Selec5 = spinnerModel.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
                else {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Options);
                    spinnerSections.setAdapter(arrayAdapter);
                    spinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter aa = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, Cars);
                            spinnerCars.setAdapter(aa);
                            spinnerCars.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if(position==0)
                                    {//_________________________________________________________________++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                        spinnerSeries.setVisibility(View.GONE);
                                        spinnerModel.setVisibility(View.GONE);
                                        selectmod.setVisibility(View.GONE);
                                        selecSeries.setVisibility(View.GONE);
                                        Select2=spinnerSections.getSelectedItem().toString();
                                        Select3=spinnerCars.getSelectedItem().toString();
                                        Selec4="";
                                        Selec5="";

                                    }
                                    else if (position == 1) {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        selectmod.setVisibility(View.VISIBLE);
                                        selecSeries.setVisibility(View.VISIBLE);
                                        ArrayAdapter o = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, BmwSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Select2 = spinnerSections.getSelectedItem().toString();
                                                        Select3 = spinnerCars.getSelectedItem().toString();
                                                        Selec4 = spinnerSeries.getSelectedItem().toString();
                                                        Selec5 = spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    } else if (position == 2) {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        selectmod.setVisibility(View.VISIBLE);
                                        selecSeries.setVisibility(View.VISIBLE);
                                        ArrayAdapter o = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, MercedsSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Select2 = spinnerSections.getSelectedItem().toString();
                                                        Select3 = spinnerCars.getSelectedItem().toString();
                                                        Selec4 = spinnerSeries.getSelectedItem().toString();
                                                        Selec5 = spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                    } else if (position == 3) {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        selectmod.setVisibility(View.VISIBLE);
                                        selecSeries.setVisibility(View.VISIBLE);
                                        ArrayAdapter o = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, AudiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Select2 = spinnerSections.getSelectedItem().toString();
                                                        Select3 = spinnerCars.getSelectedItem().toString();
                                                        Selec4 = spinnerSeries.getSelectedItem().toString();
                                                        Selec5 = spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                    } else if (position == 4) {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        selectmod.setVisibility(View.VISIBLE);
                                        selecSeries.setVisibility(View.VISIBLE);
                                        ArrayAdapter o = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, ToyotaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Select2 = spinnerSections.getSelectedItem().toString();
                                                        Select3 = spinnerCars.getSelectedItem().toString();
                                                        Selec4 = spinnerSeries.getSelectedItem().toString();
                                                        Selec5 = spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    } else if (position == 5) {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        selectmod.setVisibility(View.VISIBLE);
                                        selecSeries.setVisibility(View.VISIBLE);
                                        ArrayAdapter o = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, HondaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Select2 = spinnerSections.getSelectedItem().toString();
                                                        Select3 = spinnerCars.getSelectedItem().toString();
                                                        Selec4 = spinnerSeries.getSelectedItem().toString();
                                                        Selec5 = spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    } else if (position == 6) {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        selectmod.setVisibility(View.VISIBLE);
                                        selecSeries.setVisibility(View.VISIBLE);
                                        ArrayAdapter o = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, KiaSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Select2 = spinnerSections.getSelectedItem().toString();
                                                        Select3 = spinnerCars.getSelectedItem().toString();
                                                        Selec4 = spinnerSeries.getSelectedItem().toString();
                                                        Selec5 = spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    } else if (position == 7) {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        selectmod.setVisibility(View.VISIBLE);
                                        selecSeries.setVisibility(View.VISIBLE);
                                        ArrayAdapter o = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, HuyndaiSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Select2 = spinnerSections.getSelectedItem().toString();
                                                        Select3 = spinnerCars.getSelectedItem().toString();
                                                        Selec4 = spinnerSeries.getSelectedItem().toString();
                                                        Selec5 = spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    } else if (position == 8) {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        selectmod.setVisibility(View.VISIBLE);
                                        selecSeries.setVisibility(View.VISIBLE);
                                        ArrayAdapter o = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, ChevroletSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Select2 = spinnerSections.getSelectedItem().toString();
                                                        Select3 = spinnerCars.getSelectedItem().toString();
                                                        Selec4 = spinnerSeries.getSelectedItem().toString();
                                                        Selec5 = spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                    } else if (position == 9) {
                                        spinnerModel.setVisibility(View.VISIBLE);
                                        spinnerSeries.setVisibility(View.VISIBLE);
                                        selectmod.setVisibility(View.VISIBLE);
                                        selecSeries.setVisibility(View.VISIBLE);
                                        ArrayAdapter o = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, DodgeSeries);
                                        spinnerSeries.setAdapter(o);
                                        spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                ArrayAdapter oe = new ArrayAdapter(second_brows.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                                                spinnerModel.setAdapter(oe);
                                                spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Select2 = spinnerSections.getSelectedItem().toString();
                                                        Select3 = spinnerCars.getSelectedItem().toString();
                                                        Selec4 = spinnerSeries.getSelectedItem().toString();
                                                        Selec5 = spinnerModel.getSelectedItem().toString();
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });
                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                /*Select2 =spinnerSections.getSelectedItem().toString();
                Select3 =spinnerCars.getSelectedItem().toString();
                */

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
        // ____________________END IF ELSE HERE _________________________________________________________________
        // ____________________END IF ELSE HERE _________________________________________________________________
        // ____________________END IF ELSE HERE _________________________________________________________________
        // ____________________END IF ELSE HERE _________________________________________________________________
        // ____________________END IF ELSE HERE _________________________________________________________________
        // ____________________END IF ELSE HERE _________________________________________________________________

                searchbtnbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            if(TextUtils.isEmpty(searchField.getText()))
                            {
                                Toast.makeText(second_brows.this,"أدخل مقطع من القطعة التي تريد البحث فيها ..",Toast.LENGTH_SHORT).show();
                            }
                            else{

                                  Intent intent = new Intent(second_brows.this,final_brows.class);
                                 // intent.putExtra("code","1");
                                  intent.putExtra("field",searchField.getText());
                                  intent.putExtra("Category", Category);
                                  intent.putExtra("Selec2", "");
                                  intent.putExtra("Selec3", "");
                                  intent.putExtra("Selec4", "");
                                  intent.putExtra("Selec5", "");
                                  intent.putExtra("code","3");
                                  startActivity(intent);
                            }
                    }
                });






        try {
            search_btn = (Button) findViewById(R.id.search_btn);
            search_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Select2.equals("ـــــــــ"))
                    {
                     Toast.makeText(second_brows.this,"الرجاء اختيار القطعة ",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent(second_brows.this, final_brows.class);
                        intent.putExtra("field", "");
                        intent.putExtra("Category", Category);
                        intent.putExtra("Selec2", Select2);
                        intent.putExtra("Selec3", Select3);
                        intent.putExtra("Selec4", Selec4);
                        intent.putExtra("Selec5", Selec5);
                        intent.putExtra("code", "0");
                        intent.putExtra("location", "");
                        startActivity(intent);
                    }
                  //  Toast.makeText(second_brows.this, "ooooCategory" + Category + "  Selec2" + Select2 + "  Selec3" + Select3 + "  Selec4" + Selec4 + "  Selec5" + Selec5, Toast.LENGTH_LONG).show();
                }
            });
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    public static void hideKeyboardFrom(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }











    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu_second_final,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id  = item.getItemId();
        if(id == R.id.go_to_home);
        {
            Intent intent =new Intent(second_brows.this,page2.class);
            finish();
            startActivity(intent);

        }
         if(id==android.R.id.home){
            Intent myIntent = new Intent(getApplicationContext(), first_brows.class);
            startActivityForResult(myIntent, 0);

        }
        return true;

    }


}
