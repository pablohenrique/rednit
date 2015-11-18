package com.rednit.app.View;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.rednit.app.Controller.DownloadImageTask;
import com.rednit.app.R;
import com.rednit.app.Util.CustomJSONArrayRequest;
import com.rednit.app.Util.CustomJSONObjectRequest;
import com.rednit.app.Util.CustomVolleyRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment
        extends Fragment
        implements Response.Listener,
        Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private ImageView largeCircle;
    private ImageView mediumCircle;
    private ImageView smallCircle;
    private RequestQueue mQueue;

    private OnFragmentInteractionListener mListener;

    private View rootView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        largeCircle = (ImageView) rootView.findViewById(R.id.home_img_large_circle);
        mediumCircle = (ImageView) rootView.findViewById(R.id.home_img_medium_circle);
        smallCircle = (ImageView) rootView.findViewById(R.id.home_img_small_circle);

        animate(largeCircle, true);
        animate(mediumCircle, true);
        animate(smallCircle, true);

        mQueue = CustomVolleyRequestQueue.prepareInstance(HomeFragment.this.getActivity()).getRequestQueue();
        String url = "http://54.88.31.160:3000/api/accounts";
//        CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET, url, new JSONObject(), HomeFragment.this, HomeFragment.this);
        CustomJSONArrayRequest jsonRequest = new CustomJSONArrayRequest(Request.Method.GET, url, new JSONObject(), HomeFragment.this, HomeFragment.this);
        jsonRequest.setTag( HomeFragment.this.getClass().getName() );

        new DownloadImageTask((ImageView) rootView.findViewById(R.id.home_img_profile_circle) ).execute("https://fbcdn-sphotos-c-a.akamaihd.net/hphotos-ak-xpa1/v/t1.0-9/12227650_10205587203534851_6504001002331463838_n.jpg?oh=25879438ca96cb696538117703ad719a&oe=56EFDA46&__gda__=1458285392_15704eb827c39039edcecd7f0821ac94");
        mQueue.add(jsonRequest);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(HomeFragment.this.getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response) {
        try{
            Toast.makeText(HomeFragment.this.getActivity(), ((JSONObject)response).toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception ex){
            Toast.makeText(HomeFragment.this.getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void animate(final ImageView imageView, final boolean forever) {

        Animation scaleDown = AnimationUtils.loadAnimation(HomeFragment.this.getActivity(), R.anim.scale_down);
        Animation scaleUp = AnimationUtils.loadAnimation(HomeFragment.this.getActivity(), R.anim.scale_up);

        AnimationSet animation = new AnimationSet(false); // change to false
        animation.addAnimation(scaleDown);
        animation.addAnimation(scaleUp);
        animation.setRepeatCount(1);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (forever){
                    animate(imageView, forever);  //Calls itself to start the animation all over again in a loop if forever = true
                }
            }
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }
        });
    }

}
