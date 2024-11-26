import express from 'express';
import { exec } from 'child_process';
import cors from 'cors';
import path from 'path';

// Create the __dirname equivalent using import.meta.url
const __dirname = path.dirname(new URL(import.meta.url).pathname);

// Adjust for Windows by normalizing the path (removes the leading / on Windows)
const normalizedDirname = __dirname.startsWith('/') ? __dirname.slice(1) : __dirname;

const app = express();
const port = 3100;

// Middleware to serve static files from the "public" directory
app.use(express.static(path.join(normalizedDirname, 'public')));  // Serve static files from the public folder

// Enable CORS and parse JSON
app.use(cors());
app.use(express.json());

// Define a Route to serve the index.html
app.get('/', (req, res) => {
  // Use path.resolve to ensure absolute path to index.html
  const filePath = path.resolve(normalizedDirname, 'public', 'index.html');
  res.sendFile(filePath);  // Serve index.html with an absolute path
});

// Defining a Route to Trigger Java Program Execution
app.get('/TestProgram', (req, res) => {
  console.log('Running /TestProgram');
  // Executing a Java Program
  exec('java -cp ./public TestProgram', (err, stdout, stderr) => {
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
  });
});

app.post('/TestProgram', (req, res) => {
  console.log('Running /TestProgram(Post)');
  const { requestInput } = req.body;

  console.log(`Input is: ${requestInput}`);
  if (!requestInput) {
    return res.status(400).send('No inputData provided');
  }
  // Executing a Java Program
  exec(`java -cp ./public TestProgram ${requestInput || ''}`, (err, stdout, stderr) => {
    if (err) {
      console.log('Error executing Java file: ' + err);
      res.status(500).send('Error executing Java file: ' + err);
      return;
    }
    if (stderr) {
      console.log('stderr: ' + stderr);
      res.status(500).send('stderr: ' + stderr);
      return;
    }
    // Returns the output of a Java program
    res.status(200).send(stdout);
  });
});

// Start Server
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
