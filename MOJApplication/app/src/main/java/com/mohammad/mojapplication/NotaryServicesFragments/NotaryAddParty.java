package com.mohammad.mojapplication.NotaryServicesFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammad.mojapplication.Communicator;
import com.mohammad.mojapplication.CommunicatorService;
import com.mohammad.mojapplication.MOJManager;
import com.mohammad.mojapplication.Objects.NIDCard;
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

import java.util.Random;

/**
 * Created by alisa on 11/19/2015.
 */
public class NotaryAddParty extends Fragment {

    private LinearLayout loAddById;
    private Button btnAddParty;
    private Spinner spPartyTypeMan, spIdDoc,spPartyType;
    private CommunicatorService communicatorService;
    private RadioGroup rbgParty;
    private RadioButton rbMan,rbId;
    private ScrollView scrollView;
    private EditText etNameAddPartyMan,etAddMobileAddPartyMan,etAddresssAddPartyMan,etNIDCardAddParty;
    private ImageView ivEtNameAddPartyManTrue,ivEtAddMobileAddPartyManTrue,ivEtAddressAddPartyManTrue,
                      ivEtNidCardAddPartyTrue,ivSpPartyTypeManTrue,ivSpIdDocTrue,ivSpPartyTypeTrue;
    private TextView tvEtNameAddPartyManStar,tvEtAddMobileAddPartyManStar,tvEtAddressAddPartyManStar,
                     tvEtNidCardAddPartyStar,tvSpPartyTypeManStar,tvSpIdDocStar,tvSpPartyTypeStar;
    private int one, two,three,four;
    private String id = "";
    private Party party2;
    private MOJManager mojManager;
    private User user;

    private String imageString;




    public void receiveExtra(int one,int two) {

        this.one = one;
        this.two = two;

    }
    public void receiveExtraSecond(Party party,int one,int two) {

        this.one = one;
        this.two = two;
        this.party2 = party;

    }

    private View init(View v) {
        ivSpPartyTypeManTrue = (ImageView) v.findViewById(R.id.ivspPartyTypeManTrue);
        ivSpIdDocTrue = (ImageView) v.findViewById(R.id.ivSpIdDocTrue);
        ivSpPartyTypeTrue = (ImageView) v.findViewById(R.id.ivSpPartyTypeTrue);
        ivEtNameAddPartyManTrue = (ImageView) v.findViewById(R.id.ivEtNameAddPartyManTrue);
        ivEtAddMobileAddPartyManTrue = (ImageView) v.findViewById(R.id.ivEtMobileAddPartyManTrue);
        ivEtAddressAddPartyManTrue = (ImageView) v.findViewById(R.id.ivEtAddressAddPartyManTrue);
        ivEtNidCardAddPartyTrue = (ImageView) v.findViewById(R.id.ivNidCardPartyTrue);
        tvEtNameAddPartyManStar = (TextView) v.findViewById(R.id.tvEtNameAddPartyManStar);
        tvEtAddMobileAddPartyManStar = (TextView) v.findViewById(R.id.tvEtMobileAddPartyManStar);
        tvEtAddressAddPartyManStar = (TextView) v.findViewById(R.id.tvEtAddressAddPartyManStar);
        tvEtNidCardAddPartyStar = (TextView) v.findViewById(R.id.tvEtNidCardAddPartyStar);
        tvSpPartyTypeManStar = (TextView) v.findViewById(R.id.tvspPartyTypeManStar);
        tvSpIdDocStar = (TextView) v.findViewById(R.id.tvspIdDocStar);
        tvSpPartyTypeStar = (TextView) v.findViewById(R.id.tvSpPartyTypeStar);
        loAddById = (LinearLayout) v.findViewById(R.id.loAddById);
        spPartyType = (Spinner) v.findViewById(R.id.spPartyType);
        spIdDoc = (Spinner) v.findViewById(R.id.spidDoc);
        spPartyTypeMan = (Spinner) v.findViewById(R.id.spPartyTypeMan);
        rbgParty = (RadioGroup) v.findViewById(R.id.rbgParty);
        rbId = (RadioButton) v.findViewById(R.id.rbID);
        rbMan = (RadioButton) v.findViewById(R.id.rbMan);
        scrollView = (ScrollView) v.findViewById(R.id.svMan);
        etNameAddPartyMan = (EditText) v.findViewById(R.id.etNameAddPartyMan);
        etAddMobileAddPartyMan = (EditText) v.findViewById(R.id.etMobileAddPartyMan);
        etAddresssAddPartyMan = (EditText) v.findViewById(R.id.etAddressAddPartyMan);
        etNIDCardAddParty = (EditText) v.findViewById(R.id.etNidCardAddParty);
        btnAddParty = (Button) v.findViewById(R.id.btnAddtolist);

        ivEtNameAddPartyManTrue.setVisibility(View.GONE);
        ivEtAddMobileAddPartyManTrue.setVisibility(View.GONE);
        ivEtAddressAddPartyManTrue.setVisibility(View.GONE);
        ivEtNidCardAddPartyTrue.setVisibility(View.GONE);
        ivSpPartyTypeManTrue.setVisibility(View.GONE);
        ivSpIdDocTrue.setVisibility(View.GONE);
        ivSpPartyTypeTrue.setVisibility(View.GONE);

        return v;
    }

    private void validator() {
        spPartyTypeMan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    ivSpPartyTypeManTrue.setVisibility(View.VISIBLE);
                    tvSpPartyTypeManStar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
                spIdDoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            ivSpIdDocTrue.setVisibility(View.VISIBLE);
                            tvSpIdDocStar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        spPartyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    ivSpPartyTypeTrue.setVisibility(View.VISIBLE);
                    tvSpPartyTypeStar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etNameAddPartyMan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 3) {
                    ivEtNameAddPartyManTrue.setVisibility(View.VISIBLE);
                    tvEtNameAddPartyManStar.setVisibility(View.GONE);
                }
            }
        });
                etAddMobileAddPartyMan.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}
                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length() == 10) {
                            ivEtAddMobileAddPartyManTrue.setVisibility(View.VISIBLE);
                            tvEtAddMobileAddPartyManStar.setVisibility(View.GONE);
                        }
                    }
                });
        etAddresssAddPartyMan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 6) {
                    ivEtAddressAddPartyManTrue.setVisibility(View.VISIBLE);
                    tvEtAddressAddPartyManStar.setVisibility(View.GONE);
                }
            }
        });

        etNIDCardAddParty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 15) {
                    ivEtNidCardAddPartyTrue.setVisibility(View.VISIBLE);
                    tvEtNidCardAddPartyStar.setVisibility(View.GONE);
                }

            }
        });

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mojManager = MOJManager.getMOJManager(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_services_pg_add, container, false);
        communicatorService = (CommunicatorService) getActivity();
        init(v);
        validator();





        //picture Button


//        //restore values
//        if (selectedImage != null) {
//            imageString = selectedImage.toString();
//            one = getActivity().getIntent().getIntExtra("int1", one);
//            two = getActivity().getIntent().getIntExtra("int2", two);
//            three = getActivity().getIntent().getIntExtra("int3", 0);
//            four = getActivity().getIntent().getIntExtra("int4",0);
//            id = getActivity().getIntent().getStringExtra("ID");
//
//
//        }


        spPartyType.setSelection(three);
        spPartyTypeMan.setSelection(three);
        spIdDoc.setSelection(four);
        etNIDCardAddParty.setText(id);



        //initial shown and hidden
        scrollView.setVisibility(View.GONE);
        loAddById.setVisibility(View.VISIBLE);



        //initial add party by ID
        rbgParty.check(R.id.rbID);
        if(rbgParty.getCheckedRadioButtonId() == R.id.rbID)
        {
            btnAddParty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (etNIDCardAddParty.getText().toString().equals("")
                            || etNIDCardAddParty.getText().toString().equals(null)
                           ) {
                        Toast.makeText(getActivity(), "Please provide an ID and Scanned Document", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        NIDCard nidCard = mojManager.findNIDCardById(etNIDCardAddParty.getText().toString());

                        Random ran = new Random();
                        int randID = ran.nextInt(999999 - 91111) + 91111;

                        Party party = new Party(randID + "", nidCard.getName(), spPartyType.getSelectedItem().toString()
                                , nidCard.getMobile(), nidCard.getAddress(), "");
                        if(party2 == null)
                        {
                            communicatorService.backFromAdd(party,one,two);
                        }
                        else
                        {
                            communicatorService.backFromAddTwo(party2,party, one, two);
                        }

                    }
                }
            });

        }


        //add by id or manually RBG
        rbgParty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbID) {

                    scrollView.setVisibility(View.GONE);
                    loAddById.setVisibility(View.VISIBLE);
                    btnAddParty.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etNIDCardAddParty.getText().toString().equals("")
                                    || etNIDCardAddParty.getText().toString().equals(null)) {
                                Toast.makeText(getActivity(), "Please provide an ID and Scanned Document", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                NIDCard nidCard = mojManager.findNIDCardById(etNIDCardAddParty.getText().toString());

                                Random ran = new Random();
                                int randID = ran.nextInt(999999 - 91111) + 91111;

                                Party party = new Party(randID + "", nidCard.getName(), spPartyType.getSelectedItem().toString()
                                        , nidCard.getMobile(), nidCard.getAddress(), "");
                                if(party2 == null)
                                {
                                    communicatorService.backFromAdd(party,one,two);
                                }
                                else
                                {
                                    communicatorService.backFromAddTwo(party2,party, one, two);
                                }

                            }

                        }
                    });
                }
                else if (checkedId == R.id.rbMan) {

                    scrollView.setVisibility(View.VISIBLE);
                    loAddById.setVisibility(View.GONE);
                    btnAddParty.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {




                            if (etNameAddPartyMan.getText().toString().equals(null) ||
                                    etNameAddPartyMan.getText().toString().equals("") ||
                                    etAddMobileAddPartyMan.getText().toString().equals(null) ||
                                    etAddMobileAddPartyMan.getText().toString().equals("") ||
                                    etAddresssAddPartyMan.getText().toString().equals(null) ||
                                    etAddresssAddPartyMan.getText().toString().equals("")
                                    ) {
                                Toast.makeText(getActivity(), "Please Fill in all the fields and attach a picture",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Random ran = new Random();
                                int randID  = ran.nextInt(999999 - 91111) +91111;
                                Party party = new Party(randID + "", etNameAddPartyMan.getText().toString()
                                        , spPartyTypeMan.getSelectedItem().toString()
                                        , etAddMobileAddPartyMan.getText().toString(),
                                        etAddresssAddPartyMan.getText().toString(),"");


                                if(party2 == null)
                                {
                                    communicatorService.backFromAdd(party,one,two);
                                }
                                else
                                {
                                    communicatorService.backFromAddTwo(party2,party, one, two);
                                }
                            }


                        }
                    });

                }
            }
        });

//        btnPic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
////                communicatorService.PartyToCam(one,two,spPartyType.getSelectedItemPosition(),etNIDCardAddParty.getText().toString());
//                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                i.putExtra("int1", one);
//                i.putExtra("int2", two);
//                i.putExtra("int3", three);
//                i.putExtra("ID", id);
//                startActivityForResult(i, 111);
//            }
//        });



            return v;
    }



}




