package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Prevalent.NUserPrevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firestore.v1.TargetOrBuilder;

import java.util.HashMap;
import java.util.Map;

public class addcar extends AppCompatActivity {
    private Button addMyCar_btn;
    private String Select2, Select3, Selec4, Selec5;
    private ProgressDialog loading;
    private TextView close_btn;
    private Spinner spinnerCars, spinnerSeries, spinnerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);
        loading=new ProgressDialog(this);
        spinnerCars = (Spinner) findViewById(R.id.no3cari);
        spinnerSeries = (Spinner) findViewById(R.id.fe2acari);
        addMyCar_btn=(Button)findViewById(R.id.addMyCarBtn);
        spinnerModel = (Spinner) findViewById(R.id.modelcari);
        close_btn = (TextView) findViewById(R.id.close_addcar);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final String Cars[] = {"", "BMW", "Mercedes", "Audi", "Toyota", "Honda", "Kia", "Hyundai", "Chevrolet", "Dodge"};

        final String[] BmwSeries = {"الفئة الثالثة", "الفئة الرابعة", "الفئة الخامسة", "الفئة السادسة", "الفئة السابعة", "X3", "X5", "X6"};
        final String[] MercedsSeries = {"الفئة A", "الفئة C سيدان", "الفئة E سيدان", "الفئة S سيدان", "CLA", "الفئة C كوبيه", "الفئة E كوبيه", "الفئة S كوبيه", "GLC", "GLA", "G"};
        final String[] ToyotaSeries = {"لاند كروزر", "كورولا", "كراون", "ميراي", "راف فور ", "فورتشنر ", "هايلكس ", "يارس ", "كامري", "افالون ", "بريوس", "برادو "};
        final String[] HuyndaiSeries = {"اكسنت", "ألينترا", "توسان", "سوناتا", "جينسس"};
        final String[] AudiSeries = {"RS5", "R8", "S8", "KIO", "Q5"};
        final String[] HondaSeries = {"أكورد", "سيفيك", "سيتي", "بايول", "اتش ار في", "كروس تور", "اوديسي"};
        final String[] ChevroletSeries = {"سبارك", "أفيو", "أوبترا", "ابيكا", "لومينا", "كابرس", "كورفيت"};
        final String[] carNull = {""};
        final String[] OilNum = {"5W-30", "5W-40", "10W-40", "0W-30", "15W-40", "0W-40", "5W-20", "0W-20", "10W-30"};
        final String[] DodgeSeries = {"افيجر", "تشارجر", "تشالنجر", "داكوتا", "رام", "شادو", "ماغنوم"};
        final String[] KiaSeries = {"اوبتيما", "افيلا", "اسيا", "بيكانتو", "بنجو", "بيستا", "جراند بيرد", "ريو", "سبورتاج", "سيراتو", "سيفيا", "كوريس"};
        final String LoLYears[] = {"1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};
//_________________________________________________________________________________________________________________________________________________________________________________________________
        //_________________________________________________________________________________________________________________________________________________________________________________________________


        ArrayAdapter aa = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, Cars);
        spinnerCars.setAdapter(aa);
        spinnerCars.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    spinnerSeries.setVisibility(View.GONE);
                    spinnerModel.setVisibility(View.GONE);

                    // Select2=spinnerSections.getSelectedItem().toString();
                    Select3 = spinnerCars.getSelectedItem().toString();
                    Selec4 = "";
                    Selec5 = "";

                } else if (position == 1) {
                    spinnerModel.setVisibility(View.VISIBLE);
                    spinnerSeries.setVisibility(View.VISIBLE);
                    ArrayAdapter o = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, BmwSeries);
                    spinnerSeries.setAdapter(o);
                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter oe = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                            spinnerModel.setAdapter(oe);
                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // Select2 = spinnerSections.getSelectedItem().toString();
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
                    ArrayAdapter o = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, MercedsSeries);
                    spinnerSeries.setAdapter(o);
                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter oe = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                            spinnerModel.setAdapter(oe);
                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //   Select2 = spinnerSections.getSelectedItem().toString();
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
                    ArrayAdapter o = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, AudiSeries);
                    spinnerSeries.setAdapter(o);
                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter oe = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                            spinnerModel.setAdapter(oe);
                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //    Select2 = spinnerSections.getSelectedItem().toString();
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
                    ArrayAdapter o = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, ToyotaSeries);
                    spinnerSeries.setAdapter(o);
                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter oe = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                            spinnerModel.setAdapter(oe);
                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //     Select2 = spinnerSections.getSelectedItem().toString();
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
                    ArrayAdapter o = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, HondaSeries);
                    spinnerSeries.setAdapter(o);
                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter oe = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                            spinnerModel.setAdapter(oe);
                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //      Select2 = spinnerSections.getSelectedItem().toString();
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
                    ArrayAdapter o = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, KiaSeries);
                    spinnerSeries.setAdapter(o);
                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter oe = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                            spinnerModel.setAdapter(oe);
                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //       Select2 = spinnerSections.getSelectedItem().toString();
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
                    ArrayAdapter o = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, HuyndaiSeries);
                    spinnerSeries.setAdapter(o);
                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter oe = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                            spinnerModel.setAdapter(oe);
                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //llllllllllllllll   Select2 = spinnerSections.getSelectedItem().toString();
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
                    ArrayAdapter o = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, ChevroletSeries);
                    spinnerSeries.setAdapter(o);
                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter oe = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                            spinnerModel.setAdapter(oe);
                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //   Select2 = spinnerSections.getSelectedItem().toString();
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
                    ArrayAdapter o = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, DodgeSeries);
                    spinnerSeries.setAdapter(o);
                    spinnerSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter oe = new ArrayAdapter(addcar.this, android.R.layout.simple_spinner_dropdown_item, LoLYears);
                            spinnerModel.setAdapter(oe);
                            spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //     Select2 = spinnerSections.getSelectedItem().toString();
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


                addMyCar_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UploadMyCar();
                    }
                });






    }
    public void UploadMyCar()
    {
        loading.setTitle("يرجى الانتظار");
        loading.setMessage("جار إضافة تفاصيل سيارتك ....");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
     final    FirebaseFirestore db =FirebaseFirestore.getInstance();
        DocumentReference reference=db.collection("UCars").document(NUserPrevalent.currentOnlineNUsers.getNphone());
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot snap =task.getResult();

                     Map<String,Object> data =new HashMap<>();
                     data.put("CarType",Select3);
                     data.put("CarSeries",Selec4);
                     data.put("CarModel",Selec5);
                     data.put("CarType_CarSeries_CarModel",Select3+"_"+Selec4+"_"+Selec5);
                     db.collection("UCars").document(NUserPrevalent.currentOnlineNUsers.getNphone()).set(data, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void aVoid) {
                             loading.dismiss();
                             Toast.makeText(addcar.this,"تمت عملية الإضافة بنجاح ",Toast.LENGTH_SHORT).show();
                             finish();

                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             loading.dismiss();
                             Toast.makeText(addcar.this,"SON OF BITCH FUCKING  ERROR  ",Toast.LENGTH_SHORT).show();

                         }
                     });
                 }

        });
    }

}
    
            
            
            
            
            
            
            


