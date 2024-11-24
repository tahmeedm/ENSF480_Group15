//in order to running server, please use command: 'node server.js'. Others might not work. 
import express from 'express';
import { exec } from 'child_process';
import cors from 'cors';

import path from 'path';
const app = express();
app.use(cors());
app.use(express.json())
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

app.post('/TestProgram', (req, res) => {//This is the test URL for running TestProgram.class URL:http://localhost:3000/TestProgram
  console.log('Running /TestProgram(Post)');
  const {requestInput} = req.body;
  // const requestInput = req.body.inputData;

  console.log(`Input is: ${requestInput}`);
  if (!requestInput) {
    return res.status(400).send('No inputData provided');
  }
  // Executing a Java Program
  exec(`java -cp ./public TestProgram ${requestInput || ''}`, (err, stdout, stderr) => {//This is the command input, and will send output to client.
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

app.post('/RegisteredUser', (req, res) => {//This is the test URL for running TestProgram.class URL:http://localhost:3000/RegisteredUser
  console.log('Running /RegisteredUser');
  const {modeType, email, address, paymentInfo, password, username } = req.body;

  if (!modeType) {
    return res.status(400).send('No inputData provided');
  }
  // Executing a Java Program
  // path: ./ENSF480_Domain/bin use public for test.
  exec(`java -cp ./public RegisteredUser ${modeType || 'null'} ${email || 'null'} ${address || 'null'} ${paymentInfo || 'null'} ${password || 'null'} ${username || 'null'}`, (err, stdout, stderr) => {//This is the command input, and will send output to client.
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
    // res.status(200).json({ message: 'Java process started' });
  });
});

app.post('/Seat', (req, res) => {//This is the test URL for running TestProgram.class URL:http://localhost:3000/Seat
  console.log('Running /Seat');
  const {modeType, email, username, theater, screen, seatNumber } = req.body;

  if (!modeType) {
    return res.status(400).send('No inputData provided');
  }

  if(modeType === "Book"){
    return res.status(200).send("For Booking, backend need implement.");
  }
  // Executing a Java Program
  // path: ./ENSF480_Domain/bin use public for test.
  exec(`java -cp ./public Seat ${modeType || 'null'} ${email || 'null'} ${username || 'null'} ${theater || 'null'} ${screen || 'null'} ${seatNumber || 'null'} `, (err, stdout, stderr) => {//This is the command input, and will send output to client.
    if (err) {
      res.status(500).send('Error executing Java file: ' + err);
      return res.status(200).send("For Seats, backend need implement.")
      return;
    }
    if (stderr) {
      res.status(500).send('stderr: ' + stderr);
      return;
    }

    // Returns the output of a Java program
    res.status(200).send(stdout);
    // res.status(200).json({ message: 'Java process started' });
  });
});

app.post('/Payment', (req, res) => {//This is the test URL for running TestProgram.class URL:http://localhost:3000/Payment
  console.log('Running /Payment');
  const {modeType, cardNumber, email, cvv, expiryDate  } = req.body;

  if (!modeType) {
    return res.status(400).send('No inputData provided');
  }

  if(modeType === "check"){
    return res.status(200).send("For payment check, backend need implement.");
  }
  if(modeType === "record"){
    return res.status(200).send("For payment record, backend need implement.");
  }
  // Executing a Java Program
  // path: ./ENSF480_Domain/bin use public for test.
  exec(`java -cp ./public PaymentInfo ${modeType || 'null'} ${email || 'null'} ${cardNumber || 'null'} ${cvv || 'null'} ${expiryDate || 'null'}`, (err, stdout, stderr) => {//This is the command input, and will send output to client.
    if (err) {
      res.status(500).send('Error executing Java file: ' + err);
      return res.status(200).send("For Payment, backend need implement.")
      return;
    }
    if (stderr) {
      res.status(500).send('stderr: ' + stderr);
      return;
    }

    // Returns the output of a Java program
    res.status(200).send(stdout);
    // res.status(200).json({ message: 'Java process started' });
  });
});

// Start Server.
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
