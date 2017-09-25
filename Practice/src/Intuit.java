
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class INTUIT {
	public static void main(String[] args) {
		String[][] steps = { { "clean", "build" }, { "metadata", "binary" }, { "build", "link" }, { "link", "binary" },
				{ "clean", "metadata" }, { "build", "resources" } };

		List<List<String>> result = workflowSteps(steps);

	}

	public static List<List<String>> workflowSteps(String[][] steps) {
		List<List<String>> result = new ArrayList<List<String>>();

		Map<String, List<String>> preStepsMap = new HashMap<>();
		Set<String> set = new HashSet<>();

		// Build map for all pre-requisites
		for (int i = 0; i < steps.length; i++) {

			String val1 = steps[i][0];
			String val2 = steps[i][1];
			set.add(val1);
			set.add(val2);

			if (preStepsMap.containsKey(val2)) {

				preStepsMap.get(val2).add(val1);
			} else {

				List<String> list = new ArrayList<>();
				list.add(val2);
				preStepsMap.put(val2, list);
			}

		}

		// Loop on set

		while (!set.isEmpty()) {

			Iterator<String> itr = set.iterator();
			List<String> temp = new ArrayList<>();
			while (itr.hasNext()) {

				String val = itr.next();

				if (!preStepsMap.containsKey(val)) {
					// Means no prerequisite
					temp.add(val);
					// Delete this value from all prerequisites of Map

					deleteSteps(preStepsMap, val);

				}

			}

			for (int i = 0; i < temp.size(); i++) {
				set.remove(temp.get(i));
			}

			result.add(temp);

		}

		return result;

	}

	public static void deleteSteps(Map<String, List<String>> preStepsMap, String val) {

		List<String> remove = new ArrayList<>();
		for (String key : preStepsMap.keySet()) {
			List<String> list = preStepsMap.get(key);

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(val)) {
					list.remove(i);

				}
			}

			if (list.size() == 0) {
				remove.add(key);
			}

		}

		for (int i = 0; i < remove.size(); i++) {
			preStepsMap.remove(remove.get(i));
		}

	}

}
