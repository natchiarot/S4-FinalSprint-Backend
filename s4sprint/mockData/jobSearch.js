const fs = require("fs").promises;

(async () => {
  const { default: fetch } = await import("node-fetch");

  async function sendDataOneByOne() {
    try {
      const data = await fs.readFile("mockDataJobSearch.json", "utf8");
      const jobSearchData = JSON.parse(data);

      for (const application of jobSearchData) {
        try {
          const response = await fetch("http://localhost:8080/application", {
            method: "POST",
            headers: {
              "Content-Type": "application/json; charset=utf-8",
            },
            body: JSON.stringify(application),
          });
          if (!response.ok) {
            const responseBody = await response.text();
            console.log("Application Response status: ", response.status);
            console.error("Application Response body: ", responseBody);
          } else {
            console.log("Application Response status: ", response.status);
          }
        } catch (e) {
          console.error("Application Error: ", e);
        }
      }
    } catch (err) {
      console.error("Error reading JSON file: ", err);
    }
  }

  sendDataOneByOne();
})();
