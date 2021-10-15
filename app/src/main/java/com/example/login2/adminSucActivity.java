package com.example.login2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class adminSucActivity extends AppCompatActivity {
    TextView text;
    Button addService;
    Button deleteAccount;
    public static List<Service> services = new ArrayList<Service>( );
    public static int serviceNumber=0;
    ListView listViewServices;
    EditText eSName;
    EditText eSForm;
    EditText eSDocument;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_suc);
        text = findViewById(R.id.textView5);
        text.setText("Welcome administrator" + "  (Long click the specific added service in the list below to update or delete it.)");
        addService = findViewById(R.id.btnAdd);
        deleteAccount = findViewById(R.id.btnDeleteAccount);
        eSName = findViewById(R.id.editServiceName);
        eSForm = findViewById(R.id.editTextForms);
        eSDocument = findViewById(R.id.editTextTextPersonName3);
        listViewServices = findViewById(R.id.LVServices);
        ServiceList serviceAdapter = new ServiceList(adminSucActivity.this, services);
        listViewServices.setAdapter(serviceAdapter);
        db = new DatabaseHelper(this);

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService();
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = eSName.getText().toString();

                if(email.isEmpty()) {
                    eSName.setError("Enter The Email to Delete");
                    eSName.requestFocus();
                }
                    else{
                            boolean check = db.chkemail(email);
                            if(check == false){

                                db.delete(email);
                                Toast.makeText(getApplicationContext(),"The Deletion Is Successful",Toast.LENGTH_LONG).show();

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"The Account Does Not Exist",Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                }
        );

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Service service = services.get(position);
                showUpdateDeleteDialog(service.id,service.name);
                return true;
            }
        });

    }
    public void showUpdateDeleteDialog(int id,String ServiceName){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText editTextName = dialogView.findViewById(R.id.eSName);
        final EditText editTextForm = dialogView.findViewById(R.id.eSForm);
        final EditText editTextDocument = dialogView.findViewById(R.id.eSDocuments);
        final Button update = dialogView.findViewById(R.id.btnUpdate);
        final Button delete = dialogView.findViewById(R.id.btnDelete);
        dialogBuilder.setTitle(ServiceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String[] forms = editTextForm.getText().toString().split(",");
                String[] documents = editTextDocument.getText().toString().split(",");
                if(!TextUtils.isEmpty(name)){
                    updateService(id,name,forms,documents);
                    b.dismiss();

                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                services.remove(findServiceById(id));
                b.dismiss();
                ServiceList serviceAdapter = new ServiceList(adminSucActivity.this, services);
                listViewServices.setAdapter(serviceAdapter);
            }
        });


    }
    private void updateService(int id, String name, String[] form, String[] document){
        Service tempService = new Service(name,form,document);
        tempService.id = id ;
        services.set(id,tempService);
        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
        ServiceList serviceAdapter = new ServiceList(adminSucActivity.this, services);
        listViewServices.setAdapter(serviceAdapter);

    }
    private void addService(){
        String name = eSName.getText().toString().trim();
        String[] forms = eSForm.getText().toString().split(",");
        String[] documents = eSDocument.getText().toString().split(",");
        if (!TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(), "Service Added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Enter the name", Toast.LENGTH_LONG).show();
        }
        Service service = new Service(name, forms,documents);
        services.add(service);
        service.id = serviceNumber;
        serviceNumber++;
        eSName.setText("");
        eSForm.setText("");
        eSDocument.setText("");
        ServiceList serviceAdapter = new ServiceList(adminSucActivity.this, services);
        listViewServices.setAdapter(serviceAdapter);
    }
    public static Service findServiceById(int id){
        for(Service service:services){
            if (service.id ==id){
                return service;
            }
        }
        return null;
    }

}