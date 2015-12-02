package com.mohammad.mojapplication.NotaryServicesFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.mohammad.mojapplication.Objects.Party;
import com.mohammad.mojapplication.Objects.User;
import com.mohammad.mojapplication.R;

import java.util.Random;

/**
 * Created by alisa on 11/19/2015.
 */
public class NotaryAddParty extends Fragment {

    private LinearLayout loAddById;
    private Button btnPic,btnPicMan,btnAddParty;
    private Spinner spPartyTypeMan, spIdDoc,spPartyType;
    private CommunicatorService communicatorService;
    private RadioGroup rbgParty;
    private RadioButton rbMan,rbId;
    private ScrollView scrollView;
    private TextView tvPic,tvPicMan;
    private EditText etNameAddPartyMan,etAddMobileAddPartyMan,etAddresssAddPartyMan,
                        etNIDCardAddParty;
    private int one, two,three,four;
    private String id = "";
    private Party party2;
    private MOJManager mojManager;
    private User user;
    private Uri selectedImage;
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

        loAddById = (LinearLayout) v.findViewById(R.id.loAddById);
        btnPic = (Button) v.findViewById(R.id.btnPic);
        btnPicMan = (Button) v.findViewById(R.id.btnPicMan);
        spPartyType = (Spinner) v.findViewById(R.id.spPartyType);
        spIdDoc = (Spinner) v.findViewById(R.id.spidDoc);
        spPartyTypeMan = (Spinner) v.findViewById(R.id.spPartyTypeMan);
        rbgParty = (RadioGroup) v.findViewById(R.id.rbgParty);
        rbId = (RadioButton) v.findViewById(R.id.rbID);
        rbMan = (RadioButton) v.findViewById(R.id.rbMan);
        scrollView = (ScrollView) v.findViewById(R.id.svMan);
        tvPic = (TextView) v.findViewById(R.id.tvPicMan);
        tvPicMan = (TextView) v.findViewById(R.id.tvPic);
        etNameAddPartyMan = (EditText) v.findViewById(R.id.etNameAddPartyMan);
        etAddMobileAddPartyMan = (EditText) v.findViewById(R.id.etMobileAddPartyMan);
        etAddresssAddPartyMan = (EditText) v.findViewById(R.id.etAddressAddPartyMan);
        etNIDCardAddParty = (EditText) v.findViewById(R.id.etNidCardAddParty);
        btnAddParty = (Button) v.findViewById(R.id.btnAddtolist);






        //picture Button


        //restore values
        if (selectedImage != null) {
            imageString = selectedImage.toString();
            one = getActivity().getIntent().getIntExtra("int1", one);
            two = getActivity().getIntent().getIntExtra("int2", two);
            three = getActivity().getIntent().getIntExtra("int3", 0);
            four = getActivity().getIntent().getIntExtra("int4",0);
            id = getActivity().getIntent().getStringExtra("ID");
            tvPic.setText(imageString);
            tvPicMan.setText(imageString);

        }


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
                            || tvPic.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Please provide an ID and Scanned Document", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        user = mojManager.findUserById(getActivity().getIntent().getStringExtra("userID"));

                        Random ran = new Random();
                        int randID = ran.nextInt(999999 - 91111) + 91111;

                        Party party = new Party(randID + "", user.getName(), spPartyType.getSelectedItem().toString()
                                , user.getMobile(), user.getAddress(), imageString);
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
                    tvPicMan.setText("");
                    scrollView.setVisibility(View.GONE);
                    loAddById.setVisibility(View.VISIBLE);
                    btnAddParty.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (etNIDCardAddParty.getText().toString().equals("")
                                    || etNIDCardAddParty.getText().toString().equals(null)
                                    || tvPic.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Please provide an ID and Scanned Document", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                user = mojManager.findUserById(getActivity().getIntent().getStringExtra("userID"));

                                Random ran = new Random();
                                int randID = ran.nextInt(999999 - 91111) + 91111;

                                Party party = new Party(randID + "", user.getName(), spPartyType.getSelectedItem().toString()
                                        , user.getMobile(), user.getAddress(), imageString);
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
                    tvPicMan.setText("");
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
                                    etAddresssAddPartyMan.getText().toString().equals("") ||
                                    tvPicMan.getText().toString().equals("")
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
                                        etAddresssAddPartyMan.getText().toString(),imageString);


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

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                communicatorService.PartyToCam(one,two,spPartyType.getSelectedItemPosition(),etNIDCardAddParty.getText().toString());
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                i.putExtra("int1", one);
                i.putExtra("int2", two);
                i.putExtra("int3", three);
                i.putExtra("ID", id);
                startActivityForResult(i, 111);
            }
        });

        btnPicMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                communicatorService.PartyToCam(one,two,spPartyType.getSelectedItemPosition(),etNIDCardAddParty.getText().toString());
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                i.putExtra("int1", one);
                i.putExtra("int2", two);
                i.putExtra("int3", spPartyType.getSelectedItemPosition());
                i.putExtra("int4", spIdDoc.getSelectedItemPosition());
                i.putExtra("ID", id);
                startActivityForResult(i, 111);
            }
        });

            return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 111 && resultCode == -1&& data != null)
        {
           selectedImage = data.getData();
            tvPic.setText("Picture Attached");
            tvPicMan.setText("Picture Attached");
            imageString = selectedImage.toString();
        }

    }


}




