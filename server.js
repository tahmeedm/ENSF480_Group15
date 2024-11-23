//in order to running server, please use command: 'node server.js'. Others might not work. 
import express from 'express';
import { exec } from 'child_process';
import cors from 'cors';

import path from 'path';
const app = express();
app.use(cors());
const port = 3000;

// Defining a Route to Trigger Java Program Execution
app.get('/TestProgram', (req, res) => {//This is the test URL for running TestProgram.class URL:http://localhost:3000/TestProgram
  console.log('Running /TestProgram');
  // Executing a Java Program
  exec('java -cp ./public TestProgram ', (err, stdout, stderr) => {//This is the command input, and will send output to client.
    if (err) {
      res.status(500).send('Error executing Java file: ' + err);
      return;
    }
    if (stderr) {
      res.status(500).send('stderr: ' + stderr);
      return;
    }

    // Returns the output of a Java program
    res.status(200).send(stdout);
    // res.json({ message: 'Java process started' });
  });
});

// Start Server.
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
