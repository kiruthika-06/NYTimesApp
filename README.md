NEWYORK TIMES ANDROID APP:


Displaying a most popular view section from NY times API using this link : https://content.api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=

Most Popular Stories:
The following required functionality is completed:
User can fetch most popular viewed sections from NY times API and display it in List View
On Tap of the listed stories user can get the detailed description from that.
Here i Used MVP pattern to design the app & Retrofit library for HTTP call


MVP Architecture:


Model-View-Presenter


In MVP, the Presenter contains the UI business logic for the View. All invocations from the View delegate directly to Presenter. The Presenter is also decoupled directly from the View and talks to it through an interface. This is to allow mocking of the View in a unit test. One common attribute of MVP is that there has to be a lot of two-way dispatching. For example, when someone clicks the "Save" button, the event handler delegates to the Presenter's "OnSave" method. Once the save is completed, the Presenter will then call back the View through its interface so that the View can display that the save has completed.


Retrofit Library:

Retrofit is a REST Client library (Helper Library) used in Android and Java to create an HTTP request and also to process the HTTP response from a REST API. It was created by Square, you can also use retrofit to receive data structures other than JSON, for example SimpleXML and Jackson. Before we continue, let’s briefly define REST Client and REST API in our context.

Function to Create Retrofit instance:

public static Retrofit getRetrofitInstance() {
        
        if (retrofit == null) {
         
         retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        
        return retrofit;
    }
    
Function to get JSON data from URL:

     call.enqueue(new Callback<ResponseList>() {
            @Override
            public void onResponse(Call<ResponseList> call, Response<ResponseList> response) {
                onFinishedListener.onFinished(response.body().getResults());

                Log.wtf("URL ResponseList", response.body().toString()+ "");


            }


            @Override
            public void onFailure(Call<ResponseList> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.wtf("URL ResponseList",  call.toString() + "");
            }
        });
