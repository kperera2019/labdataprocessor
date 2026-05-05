import { useState } from 'react'

function App() {
  const [result, setResult] = useState("")

  const runProcess = async () => {
    const res = await fetch("http://localhost:8080/process")
    const text = await res.text()
    setResult(text)
    console.log(text);
  }

  return (
    <div>
      <h1>Lab Data Processor</h1>
      <button onClick={runProcess}>Run Processing</button>
      <p>{result}</p>
    </div>
  )
}

export default App