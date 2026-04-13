package com.example.avaliao;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {
    private int numberOfTasks = 0;
    private int concluidas = 0;
    private ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task);

        Button botaoadicionar = findViewById(R.id.Button_adicionar);
        Button botaoremover = findViewById(R.id.Button_remover);
        Button botaoconcluir = findViewById(R.id.Button_concluir);
        Button btnsair = findViewById(R.id.Button_sair);
        progressView = findViewById(R.id.progressView);


        botaoadicionar.setOnClickListener(this);
        botaoremover.setOnClickListener(this);
        botaoconcluir.setOnClickListener(this);
        btnsair.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Button_adicionar) {
            if (numberOfTasks < 25) {
                numberOfTasks++;
                progressView.setMaxValue(numberOfTasks);
                progressView.setProgress(concluidas);
                Toast.makeText(this, "Total de tarefas: " + numberOfTasks, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Limite de 25 tarefas atingido", Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.Button_remover) {
            if (numberOfTasks > concluidas) {
                numberOfTasks--;
                progressView.setMaxValue(numberOfTasks);
                progressView.setProgress(concluidas);
                Toast.makeText(this, "Total de tarefas: " + numberOfTasks, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Não é possível remover", Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.Button_concluir) {
            if (concluidas < numberOfTasks) {
                concluidas++;
                progressView.setMaxValue(numberOfTasks);
                progressView.setProgress(concluidas);
                Toast.makeText(this, "Concluídas: " + concluidas + "/" + numberOfTasks, Toast.LENGTH_LONG).show();

                if (concluidas == numberOfTasks && numberOfTasks > 0) {
                    progressView.setMaxValue(numberOfTasks);
                    progressView.setProgress(concluidas);
                    new android.os.Handler().postDelayed(() -> {
                        numberOfTasks = 0;
                        concluidas = 0;
                        progressView.setMaxValue(0);
                        progressView.setProgress(0);
                        Toast.makeText(TaskActivity.this, "Parabéns! Todas as tarefas concluídas!", Toast.LENGTH_LONG).show();
                    }, 1500);
                }
            } else {
                Toast.makeText(this, "Não há tarefas para concluir", Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.Button_sair) {
            finish();
        }
    }
}
